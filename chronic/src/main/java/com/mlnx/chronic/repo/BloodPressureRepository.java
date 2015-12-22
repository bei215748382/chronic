package com.mlnx.chronic.repo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.mlnx.chronic.entity.BloodPressure;
import com.mlnx.springmvc.service.CassandraService;
import com.mlnx.springmvc.util.ResultIterator;
import com.mlnx.springmvc.util.ResultTransformer;

@Service
public class BloodPressureRepository implements
		ResultTransformer<BloodPressure> {

	private static final String TABLE_NAME = "blood_pressure";

	private static enum Column {

		PATIENT_ID("patient_id", DataType.cint()), DEVICE_ID("device_id",
				DataType.text()), DATE_TIME("date_time", DataType.timestamp()), VALUE_DIASTOLIC(
				"value_diastolic", DataType.cint()), VALUE_SYSTOLIC(
				"value_systolic", DataType.cint()), HEART_RATE("heart_rate",
				DataType.cint()), STATE("state", DataType.text()), COMMENT(
				"comment", DataType.text());

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
			+ String.format("  %s,\n", Column.DATE_TIME)
			+ String.format("  %s,\n", Column.VALUE_DIASTOLIC)
			+ String.format("  %s,\n", Column.VALUE_SYSTOLIC)
			+ String.format("  %s,\n", Column.HEART_RATE)
			+ String.format("  %s,\n", Column.STATE)
			+ String.format("  %s,\n", Column.COMMENT)
			+ String.format("PRIMARY KEY ((%s),%s)\n", Column.PATIENT_ID.name,
					Column.DATE_TIME.name) + ")";

	private static final String INSERT_CQL = String.format(
			"INSERT INTO %s (\n", TABLE_NAME)
			+ String.format("  %s,\n", Column.PATIENT_ID.name)
			+ String.format("  %s,\n", Column.DEVICE_ID.name)
			+ String.format("  %s,\n", Column.DATE_TIME.name)
			+ String.format("  %s,\n", Column.VALUE_DIASTOLIC.name)
			+ String.format("  %s,\n", Column.VALUE_SYSTOLIC.name)
			+ String.format("  %s,\n", Column.HEART_RATE.name)
			+ String.format("  %s,\n", Column.STATE.name)
			+ String.format("  %s\n", Column.COMMENT.name)
			+ ") VALUES (\n"
			+ CassandraService
					.generateValuePlaceholders(Column.values().length) + "\n)";

	private static final Select FIND_ALL_QUERY = QueryBuilder.select().all()
			.from(TABLE_NAME);

	private static final String FIND_BY_PATIENT_ID_AND_DATE_NO_LIMIT_CQL = String
			.format("SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.PATIENT_ID.name);

	private static final String FIND_BY_PATIENT_ID_AND_DATE_WITH_LIMIT_CQL = FIND_BY_PATIENT_ID_AND_DATE_NO_LIMIT_CQL
			+ String.format(" AND %s < ?", Column.DATE_TIME.name)
			+ String.format(" order by %s desc", Column.DATE_TIME.name)
			+ " limit ?";

	private static final String FIND_BY_PATIENT_ID_AND_DATE_START_CQL = FIND_BY_PATIENT_ID_AND_DATE_NO_LIMIT_CQL
			+ String.format(" AND %s >= ?", Column.DATE_TIME.name);

	private static final String FIND_BY_PATIENT_ID_AND_DATE_START_AND_END_CQL = FIND_BY_PATIENT_ID_AND_DATE_START_CQL
			+ String.format(" AND %s <= ?", Column.DATE_TIME.name);

	private static final String FIND_BY_PATIENT_ID_LIMIT_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ? order by %s asc limit 1",
					Column.PATIENT_ID.name, Column.DATE_TIME.name);

	private static final String FIND_COUNT_BY_PATIENT_ID_LIMIT_CQL = String
			.format("SELECT count(1) FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.PATIENT_ID.name);

	@Autowired
	private CassandraService cassandra;

	private PreparedStatement insertQuery;

	private PreparedStatement findByPatientIdAndDateWithStartAndEndQuery;

	private PreparedStatement findByPatientIdAndDateWithEndAndLimitQuery;

	private PreparedStatement findByPatientIdLimitQuery;

	private PreparedStatement findCountByPatientIdLimitQuery;

	@PostConstruct
	private void init() throws Exception {

		cassandra.createTableIfNotExists(TABLE_NAME, CREATE_TABLE_CQL);
		insertQuery = cassandra.getSession().prepare(INSERT_CQL);
		findByPatientIdAndDateWithStartAndEndQuery = cassandra.getSession()
				.prepare(FIND_BY_PATIENT_ID_AND_DATE_START_AND_END_CQL);
		findByPatientIdAndDateWithEndAndLimitQuery = cassandra.getSession()
				.prepare(FIND_BY_PATIENT_ID_AND_DATE_WITH_LIMIT_CQL);
		findByPatientIdLimitQuery = cassandra.getSession().prepare(
				FIND_BY_PATIENT_ID_LIMIT_CQL);
		findCountByPatientIdLimitQuery = cassandra.getSession().prepare(
				FIND_COUNT_BY_PATIENT_ID_LIMIT_CQL);
	}

	public BloodPressure transform(Row row) {

		BloodPressure bloodpressure = new BloodPressure();
		bloodpressure.setPatientId(row.getInt(Column.PATIENT_ID.name));
		bloodpressure.setDeivceId(row.getString(Column.DEVICE_ID.name));
		bloodpressure.setDateTime(row.getDate(Column.DATE_TIME.name));
		bloodpressure.setValue_diastolic(row
				.getInt(Column.VALUE_DIASTOLIC.name));
		bloodpressure.setValue_systolic(row.getInt(Column.VALUE_SYSTOLIC.name));
		bloodpressure.setHeart_rate(row.getInt(Column.HEART_RATE.name));
		bloodpressure.setState(row.getString(Column.STATE.name));
		bloodpressure.setComment(row.getString(Column.COMMENT.name));
		return bloodpressure;
	}

	public void save(BloodPressure bloodpressure) {
		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		boundInsertQuery.bind(bloodpressure.getPatientId(),
				bloodpressure.getDeivceId(), bloodpressure.getDateTime(),
				bloodpressure.getValue_diastolic(),
				bloodpressure.getValue_systolic(),
				bloodpressure.getHeart_rate(), bloodpressure.getState(),
				bloodpressure.getComment());
		try {
			cassandra.getSession().execute(boundInsertQuery);
		} catch (DriverException e) {
			throw e;
		}
	}

	/**
	 * 查找所有血压值
	 * 
	 * @return
	 */
	public List<BloodPressure> findAllOrderedById() {

		ResultSet resultSet = cassandra.getSession().execute(FIND_ALL_QUERY);
		List<BloodPressure> bloodPressures = new ArrayList<BloodPressure>();
		for (Row result : resultSet) {
			BloodPressure bp = transform(result);
			bloodPressures.add(bp);
		}
		return bloodPressures;
	}

	/**
	 * 根据病人id和时间范围查找数据
	 * 
	 * @param patientId
	 * @param startDateTime
	 * @param endDateTime
	 * @return
	 */
	public Iterator<BloodPressure> findByPatientIdAndDateTimeRange(
			int patientId, Date startDateTime, Date endDateTime) {
		BoundStatement query = new BoundStatement(
				findByPatientIdAndDateWithStartAndEndQuery);
		query.bind(patientId, startDateTime, endDateTime);
		ResultSet resultSet = cassandra.getSession().execute(query);
		return new ResultIterator<BloodPressure>(resultSet, this);
	}

	/**
	 * 根据病人id、结束时间和limit限制查找病人数据
	 * 
	 * @param patientId
	 * @param endDateTime
	 * @param limit
	 * @return
	 */
	public Iterator<BloodPressure> findByPatientIdAndEndDateTimeAndLimit(
			int patientId, Date endDateTime, int limit) {
		BoundStatement query = new BoundStatement(
				findByPatientIdAndDateWithEndAndLimitQuery);
		query.bind(patientId, endDateTime, limit);
		ResultSet resultSet = cassandra.getSession().execute(query);
		return new ResultIterator<BloodPressure>(resultSet, this);
	}

	/**
	 * 获取病人第一条数据
	 * 
	 * @param patientId
	 * @return
	 */
	public BloodPressure findByPatientIdLimitOne(int patientId) {
		BoundStatement query = new BoundStatement(findByPatientIdLimitQuery);
		query.bind(patientId);
		ResultSet resultSet = cassandra.getSession().execute(query);
		return resultSet == null ? null : transform(resultSet.one());
	}

	/**
	 * 获取病人测试记录
	 * 
	 * @param patientId
	 * @return
	 */
	public Long findCountByPatientId(int patientId) {
		BoundStatement query = new BoundStatement(
				findCountByPatientIdLimitQuery);
		query.bind(patientId);
		ResultSet resultSet = cassandra.getSession().execute(query);
		return resultSet == null ? null : transformCount(resultSet.one());
	}

	private Long transformCount(Row one) {
		return one.getLong("count");
	}

}
