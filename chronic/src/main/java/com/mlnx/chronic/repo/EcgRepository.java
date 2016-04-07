package com.mlnx.chronic.repo;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.mlnx.chronic.entity.Ecg;
import com.mlnx.chronic.service.CassandraService;
import com.mlnx.chronic.util.DatePrefix;
import com.mlnx.chronic.util.ResultIterator;
import com.mlnx.chronic.util.ResultTransformer;

@Service
public class EcgRepository implements ResultTransformer<Ecg> {

    private static final String TABLE_NAME = "ecg";

    private static enum Column {

        PATIENT_ID("patient_id", DataType.cint()), DEVICE_ID("device_id",
                DataType.text()), DATE_PREFIX("date_prefix", DataType.text()), DATE_TIME(
                "date_time", DataType.timestamp()), NUM_CHANNELS(
                "num_channels", DataType.cint()), SAMPLING_RATE(
                "sampling_rate", DataType.cint()), AMPLIFICATION(
                "amplification", DataType.cint()), HEART_RATE("heart_rate",
                DataType.cint()), BLOOD_DIASTOLIC("blood_diastolic", DataType
                .cint()), BLOOD_SYSTOLIC("blood_systolic", DataType.cint()), SPO2(
                "spo2", DataType.cint()), POSE("pose", DataType.cint()), DATA(
                "data", DataType.blob());

        private String name;

        private DataType dataType;

        private Column(String name, DataType dataType) {

            this.name = name;
            this.dataType = dataType;
        }

        @Override
        public String toString() {

            return String.format("%s %s", name, dataType);
        }
    }

    private static final String CREATE_TABLE_CQL = String.format(
            "CREATE TABLE %s (\n", TABLE_NAME)
            + String.format("  %s,\n", Column.PATIENT_ID)
            + String.format("  %s,\n", Column.DEVICE_ID)
            + String.format("  %s,\n", Column.DATE_PREFIX)
            + String.format("  %s,\n", Column.DATE_TIME)
            + String.format("  %s,\n", Column.NUM_CHANNELS)
            + String.format("  %s,\n", Column.SAMPLING_RATE)
            + String.format("  %s,\n", Column.AMPLIFICATION)
            + String.format("  %s,\n", Column.HEART_RATE)
            + String.format("  %s,\n", Column.BLOOD_DIASTOLIC)
            + String.format("  %s,\n", Column.BLOOD_SYSTOLIC)
            + String.format("  %s,\n", Column.SPO2)
            + String.format("  %s,\n", Column.POSE)
            + String.format("  %s,\n", Column.DATA)
            + String.format("PRIMARY KEY ((%s, %s), %s)\n",
                    Column.PATIENT_ID.name, Column.DATE_PREFIX.name,
                    Column.DATE_TIME.name) + ")";

    private static final String INSERT_CQL = String.format(
            "INSERT INTO %s (\n", TABLE_NAME)
            + String.format("  %s,\n", Column.PATIENT_ID.name)
            + String.format("  %s,\n", Column.DEVICE_ID.name)
            + String.format("  %s,\n", Column.DATE_PREFIX.name)
            + String.format("  %s,\n", Column.DATE_TIME.name)
            + String.format("  %s,\n", Column.NUM_CHANNELS.name)
            + String.format("  %s,\n", Column.SAMPLING_RATE.name)
            + String.format("  %s,\n", Column.AMPLIFICATION.name)
            + String.format("  %s,\n", Column.HEART_RATE.name)
            + String.format("  %s,\n", Column.BLOOD_DIASTOLIC.name)
            + String.format("  %s,\n", Column.BLOOD_SYSTOLIC.name)
            + String.format("  %s,\n", Column.SPO2.name)
            + String.format("  %s,\n", Column.POSE.name)
            + String.format("  %s\n", Column.DATA.name)
            + ") VALUES (\n"
            + CassandraService
                    .generateValuePlaceholders(Column.values().length) + "\n)";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_NO_LIMIT_CQL = String
            .format("SELECT * FROM %s", TABLE_NAME)
            + String.format(" WHERE %s = ?", Column.PATIENT_ID.name)
            + String.format(" AND %s = ?", Column.DATE_PREFIX.name);

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_CQL = FIND_BY_PATIENT_ID_AND_HOUR_NO_LIMIT_CQL
            + " LIMIT 8";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_NO_LIMIT_CQL = FIND_BY_PATIENT_ID_AND_HOUR_NO_LIMIT_CQL
            + String.format(" AND %s > ?", Column.DATE_TIME.name);

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_CQL = FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_NO_LIMIT_CQL
            + " LIMIT 8";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_NO_LIMIT_CQL = FIND_BY_PATIENT_ID_AND_HOUR_NO_LIMIT_CQL
            + String.format(" AND %s < ?", Column.DATE_TIME.name);

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_CQL = FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_NO_LIMIT_CQL
            + " LIMIT 8";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_NO_LIMIT_CQL = FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_NO_LIMIT_CQL
            + String.format(" AND %s < ?", Column.DATE_TIME.name);

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_CQL = FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_NO_LIMIT_CQL
            + " LIMIT 8";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_CQL_LIMIT = FIND_BY_PATIENT_ID_AND_HOUR_NO_LIMIT_CQL
            + " LIMIT 120";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_CQL_LIMIT = FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_NO_LIMIT_CQL
            + " LIMIT 120";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_CQL_LIMIT = FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_NO_LIMIT_CQL
            + " LIMIT 120";

    private static final String FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_CQL_LIMIT = FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_NO_LIMIT_CQL
            + " LIMIT 120";

    private static final String FIND_BY_PATIENT_ID_AND_START_TIME = String
            .format("SELECT %s,%s FROM %s", Column.HEART_RATE.name,
                    Column.POSE.name, TABLE_NAME)
            + String.format(" WHERE %s = ?", Column.PATIENT_ID.name)
            + String.format(" AND %s = ?", Column.DATE_PREFIX.name)
            + String.format(" AND %s > ?", Column.DATE_TIME.name) + " LIMIT 3";

    private static final Logger log = LoggerFactory.getLogger(EcgRepository.class);

    @Autowired
    private CassandraService cassandra;

    private PreparedStatement insertQuery;

    private PreparedStatement findByPatientIdAndHourQuery;

    private PreparedStatement findByPatientIdAndHourWithStartQuery;

    private PreparedStatement findByPatientIdAndHourWithEndQuery;

    private PreparedStatement findByPatientIdAndHourWithStartAndEndQuery;

    private PreparedStatement findByPatientIdAndHourQueryLimit;

    private PreparedStatement findByPatientIdAndHourWithStartQueryLimit;

    private PreparedStatement findByPatientIdAndHourWithEndQueryLimit;

    private PreparedStatement findByPatientIdAndHourWithStartAndEndQueryLimit;

    private PreparedStatement findByPatientIdAndStartTime;

    @PostConstruct
    private void init() throws Exception {

        cassandra.createTableIfNotExists(TABLE_NAME, CREATE_TABLE_CQL);
        insertQuery = cassandra.getSession().prepare(INSERT_CQL);
        findByPatientIdAndHourQuery = cassandra.getSession().prepare(
                FIND_BY_PATIENT_ID_AND_HOUR_CQL);
        findByPatientIdAndHourWithStartQuery = cassandra.getSession().prepare(
                FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_CQL);
        findByPatientIdAndHourWithEndQuery = cassandra.getSession().prepare(
                FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_CQL);
        findByPatientIdAndHourWithStartAndEndQuery = cassandra.getSession()
                .prepare(FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_CQL);
        findByPatientIdAndHourQueryLimit = cassandra.getSession().prepare(
                FIND_BY_PATIENT_ID_AND_HOUR_CQL_LIMIT);
        findByPatientIdAndHourWithStartQueryLimit = cassandra.getSession()
                .prepare(FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_CQL_LIMIT);
        findByPatientIdAndHourWithEndQueryLimit = cassandra.getSession()
                .prepare(FIND_BY_PATIENT_ID_AND_HOUR_WITH_END_CQL_LIMIT);
        findByPatientIdAndHourWithStartAndEndQueryLimit = cassandra
                .getSession()
                .prepare(
                        FIND_BY_PATIENT_ID_AND_HOUR_WITH_START_AND_END_CQL_LIMIT);
        findByPatientIdAndStartTime = cassandra.getSession().prepare(
                FIND_BY_PATIENT_ID_AND_START_TIME);
    }

    @Override
    public Ecg transform(Row row) {

        Ecg ecg = new Ecg();
        ecg.setPatientId(row.getInt(Column.PATIENT_ID.name));
        ecg.setDeivceId(row.getString(Column.DEVICE_ID.name));
        ecg.setDateTime(row.getDate(Column.DATE_TIME.name));
        ecg.setNumChannels(row.getInt(Column.NUM_CHANNELS.name));
        ecg.setSamplingRate(row.getInt(Column.SAMPLING_RATE.name));
        ecg.setAmplification(row.getInt(Column.AMPLIFICATION.name));
        ecg.setHeartRate(row.isNull(Column.HEART_RATE.name) ? null : row
                .getInt(Column.HEART_RATE.name));
        ecg.setDiastolic(row.isNull(Column.BLOOD_DIASTOLIC.name) ? null : row
                .getInt(Column.BLOOD_DIASTOLIC.name));
        ecg.setSystolic(row.isNull(Column.BLOOD_SYSTOLIC.name) ? null : row
                .getInt(Column.BLOOD_SYSTOLIC.name));
        ecg.setSpo2(row.isNull(Column.SPO2.name) ? null : row
                .getInt(Column.SPO2.name));
        ecg.setPose(row.isNull(Column.POSE.name) ? null : row
                .getInt(Column.POSE.name));
        ecg.setData(row.getBytes(Column.DATA.name));
        return ecg;
    }

    public Iterator<Ecg> findByPatientIdAndStartDateTime(int patientId,
            Date startDateTime) {

        DatePrefix datePrefix = DatePrefix.yyyyMMddHH(startDateTime);
        BoundStatement query = new BoundStatement(
                findByPatientIdAndHourWithStartQuery);
        query.bind(patientId, datePrefix.toString(), startDateTime);
        ResultSet resultSet = cassandra.getSession().execute(query);
        while (resultSet.isExhausted()) {
            datePrefix.increment();
            DatePrefix now = DatePrefix.yyyyMMddHH(new Date());
            int diff = datePrefix.compareTo(now);
            if (diff > 0) {
                break;
            } else {
                query = new BoundStatement(findByPatientIdAndHourQuery);
                query.bind(patientId, datePrefix.toString());
                resultSet = cassandra.getSession().execute(query);
            }
        }
        return new ResultIterator<Ecg>(resultSet, this);
    }

    public Iterator<Ecg> findByPatientIdAndDateTimeRange(int patientId,
            Date startDateTime, Date endDateTime) {

        DatePrefix datePrefix = DatePrefix.yyyyMMddHH(startDateTime);
        DatePrefix endDatePrefix = DatePrefix.yyyyMMddHH(endDateTime);
        ResultSet resultSet = null;
        int diff = datePrefix.compareTo(endDatePrefix);
        if (diff == 0) {
            BoundStatement query = new BoundStatement(
                    findByPatientIdAndHourWithStartAndEndQuery);
            query.bind(patientId, datePrefix.toString(), startDateTime,
                    endDateTime);
            resultSet = cassandra.getSession().execute(query);
        } else if (diff < 0) {
            BoundStatement query = new BoundStatement(
                    findByPatientIdAndHourWithStartQuery);
            query.bind(patientId, datePrefix.toString(), startDateTime);
            resultSet = cassandra.getSession().execute(query);
            while (resultSet.isExhausted()) {
                datePrefix.increment();
                diff = datePrefix.compareTo(endDatePrefix);
                if (diff == 0) {
                    query = new BoundStatement(
                            findByPatientIdAndHourWithEndQuery);
                    query.bind(patientId, datePrefix.toString(), endDateTime);
                } else if (diff > 0) {
                    break;
                } else {
                    query = new BoundStatement(findByPatientIdAndHourQuery);
                    query.bind(patientId, datePrefix.toString());
                }
                resultSet = cassandra.getSession().execute(query);
            }
        }
        return new ResultIterator<Ecg>(resultSet, this);
    }

    /**
     * 根据起始结束时间查找ecg数据，限制120条
     * 
     * @param patientId
     * @param startDateTime
     * @param endDateTime
     * @return
     */
    public Iterator<Ecg> findByPatientIdAndDateTimeRangeLimit(int patientId,
            Date startDateTime, Date endDateTime) {

        DatePrefix datePrefix = DatePrefix.yyyyMMddHH(startDateTime);
        DatePrefix endDatePrefix = DatePrefix.yyyyMMddHH(endDateTime);
        ResultSet resultSet = null;
        int diff = datePrefix.compareTo(endDatePrefix);
        if (diff == 0) {
            BoundStatement query = new BoundStatement(
                    findByPatientIdAndHourWithStartAndEndQueryLimit);
            query.bind(patientId, datePrefix.toString(), startDateTime,
                    endDateTime);
            resultSet = cassandra.getSession().execute(query);
        } else if (diff < 0) {
            BoundStatement query = new BoundStatement(
                    findByPatientIdAndHourWithStartQueryLimit);
            query.bind(patientId, datePrefix.toString(), startDateTime);
            resultSet = cassandra.getSession().execute(query);
            while (resultSet.isExhausted()) {
                datePrefix.increment();
                diff = datePrefix.compareTo(endDatePrefix);
                if (diff == 0) {
                    query = new BoundStatement(
                            findByPatientIdAndHourWithEndQueryLimit);
                    query.bind(patientId, datePrefix.toString(), endDateTime);
                } else if (diff > 0) {
                    break;
                } else {
                    query = new BoundStatement(findByPatientIdAndHourQueryLimit);
                    query.bind(patientId, datePrefix.toString());
                }
                resultSet = cassandra.getSession().execute(query);
            }
        }
        return new ResultIterator<Ecg>(resultSet, this);
    }

    public void save(Ecg ecg) {

        DatePrefix datePrefix = DatePrefix.yyyyMMddHH(ecg.getDateTime());
        BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
        boundInsertQuery.bind(ecg.getPatientId(), ecg.getDeivceId(),
                datePrefix.toString(), ecg.getDateTime(), ecg.getNumChannels(),
                ecg.getSamplingRate(), ecg.getAmplification(),
                ecg.getHeartRate(), ecg.getDiastolic(), ecg.getSystolic(),
                ecg.getSpo2(), ecg.getPose(), ecg.getData());
        try {
            cassandra.getSession().execute(boundInsertQuery);
        } catch (DriverException e) {
//            log.info(e, "Failed to save ECG data for patient #%d at %s", ecg
//                    .getPatientId(), new SimpleDateFormat(
//                    VstpPacket.TIMESTAMP_FORMAT).format(ecg.getDateTime()));
            throw e;
        }
    }

}
