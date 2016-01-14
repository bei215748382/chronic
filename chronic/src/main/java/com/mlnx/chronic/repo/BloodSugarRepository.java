package com.mlnx.chronic.repo;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.mlnx.chronic.entity.BloodSugar;
import com.mlnx.chronic.service.CassandraService;
import com.mlnx.chronic.util.ResultIterator;
import com.mlnx.chronic.util.ResultTransformer;
@Service
public class BloodSugarRepository implements ResultTransformer<BloodSugar> {
	private static final String TABLE_NAME = "blood_sugar";

	private static enum Column {

		PATIENT_ID("patient_id", DataType.cint()), DATE_TIME("date_time",
				DataType.timestamp()), SUGAR_VALUE("sugar_value", DataType
				.text()), STATE("state", DataType.text()), MEDICINE("medicine",
				DataType.cboolean()), INSULIN("insulin", DataType.cboolean()), OTHER_MEDICINE(
				"other_medicine", DataType.text()), CARBOHYDRATE(
				"carbohydrate", DataType.cint()), EXERCISE_TIME(
				"exercise_time", DataType.text()), REMARK("remark", DataType
				.text());

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
			+ String.format("  %s,\n", Column.DATE_TIME)
			+ String.format("  %s,\n", Column.SUGAR_VALUE)
			+ String.format("  %s,\n", Column.STATE)
			+ String.format("  %s,\n", Column.MEDICINE)
			+ String.format("  %s,\n", Column.INSULIN)
			+ String.format("  %s,\n", Column.OTHER_MEDICINE)
			+ String.format("  %s,\n", Column.CARBOHYDRATE)
			+ String.format("  %s,\n", Column.EXERCISE_TIME)
			+ String.format("  %s,\n", Column.REMARK)
			+ String.format("PRIMARY KEY ((%s),%s)\n", Column.PATIENT_ID.name,
					Column.DATE_TIME.name) + ")";

	private static final String INSERT_CQL = String.format(
			"INSERT INTO %s (\n", TABLE_NAME)
			+ String.format("  %s,\n", Column.PATIENT_ID.name)
			+ String.format("  %s,\n", Column.DATE_TIME.name)
			+ String.format("  %s,\n", Column.SUGAR_VALUE.name)
			+ String.format("  %s,\n", Column.STATE.name)
			+ String.format("  %s,\n", Column.MEDICINE.name)
			+ String.format("  %s,\n", Column.INSULIN.name)
			+ String.format("  %s,\n", Column.OTHER_MEDICINE.name)
			+ String.format("  %s,\n", Column.CARBOHYDRATE.name)
			+ String.format("  %s,\n", Column.EXERCISE_TIME.name)
			+ String.format("  %s\n", Column.REMARK.name)
			+ ") VALUES (\n"
			+ CassandraService
					.generateValuePlaceholders(Column.values().length) + "\n)";

	private static final String CREATE_NAME_INDEX_CQL = String.format(
			"CREATE INDEX %s" + " ON %s ( %s );", TABLE_NAME + "_state",
			TABLE_NAME, Column.STATE.name);

	// private static final Select FIND_ALL_QUERY = QueryBuilder.select().all()
	// .from(TABLE_NAME);

	private static final String FIND_BY_PATIENT_ID_AND_DAY_NO_LIMIT_CQL = String
			.format("SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.PATIENT_ID.name);

	private static final String FIND_BY_PATIENT_ID_AND_START_TIME = FIND_BY_PATIENT_ID_AND_DAY_NO_LIMIT_CQL
			+ String.format(" AND %s > ?", Column.DATE_TIME.name);

	private static final String FIND_BY_PATIENT_ID_AND_START_TIME_AND_END_TIME = FIND_BY_PATIENT_ID_AND_START_TIME
			+ String.format(" AND %s < ?", Column.DATE_TIME.name);

	private static final String FIND_BY_PATIENT_ID_AND_START_AND_END_AND_STATE_NO_LIMIT_CQL = FIND_BY_PATIENT_ID_AND_START_TIME_AND_END_TIME
			+ String.format(" AND %s = ?", Column.STATE.name)
			+ " allow filtering";

	private static final String FIND_LAST_BY_PATIENTID_AND_STATE_AND_DATE = FIND_BY_PATIENT_ID_AND_DAY_NO_LIMIT_CQL
			+ String.format(" AND %s < ? order by %s DESC limit ?",
					Column.DATE_TIME.name,Column.DATE_TIME.name);
	
	private static final String FIND_BY_PATIENT_ID_LIMIT_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ? order by %s asc limit 1",
					Column.PATIENT_ID.name, Column.DATE_TIME.name);

	private static final String FIND_COUNT_BY_PATIENT_ID_LIMIT_CQL = String
			.format("SELECT count(1) FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.PATIENT_ID.name);

	private Logger log;

	@Autowired
	private CassandraService cassandra;

	private PreparedStatement insertQuery;

	private PreparedStatement findByPatientIdAndStartAndEndAndState;

	private PreparedStatement findLastByPatientIdAndStateAndDate;
	
	private PreparedStatement findByPatientIdLimitQuery;

	private PreparedStatement findCountByPatientIdLimitQuery;

	@PostConstruct
	private void init() throws Exception {
		cassandra.createTableIfNotExists(TABLE_NAME, CREATE_TABLE_CQL,
				CREATE_NAME_INDEX_CQL);
		insertQuery = cassandra.getSession().prepare(INSERT_CQL);
		findByPatientIdAndStartAndEndAndState = cassandra.getSession().prepare(
				FIND_BY_PATIENT_ID_AND_START_AND_END_AND_STATE_NO_LIMIT_CQL);
		findLastByPatientIdAndStateAndDate = cassandra.getSession().prepare(FIND_LAST_BY_PATIENTID_AND_STATE_AND_DATE);
		findByPatientIdLimitQuery = cassandra.getSession().prepare(
				FIND_BY_PATIENT_ID_LIMIT_CQL);
		findCountByPatientIdLimitQuery = cassandra.getSession().prepare(
				FIND_COUNT_BY_PATIENT_ID_LIMIT_CQL);
	}

	@Override
	public BloodSugar transform(Row row) {
		BloodSugar bs = new BloodSugar();
		bs.setPatient_id(row.getInt(Column.PATIENT_ID.name));
		bs.setDate(row.getDate(Column.DATE_TIME.name));
		bs.setSugar_value(row.getString(Column.SUGAR_VALUE.name));
		bs.setState(row.getString(Column.STATE.name));
		bs.setMedicine(row.getBool(Column.MEDICINE.name));
		bs.setInsulin(row.getBool(Column.INSULIN.name));
		bs.setOther_medicine(row.getString(Column.OTHER_MEDICINE.name));
		bs.setCarbohybrate(row.getInt(Column.CARBOHYDRATE.name));
		bs.setExercise_time(row.getString(Column.EXERCISE_TIME.name));
		bs.setRemark(row.getString(Column.REMARK.name));
		return bs;
	}

	/**
	 * 保存血糖
	 * 
	 * @param bloodSugar
	 */
	public void save(BloodSugar bloodSugar) {
		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		boundInsertQuery.bind(bloodSugar.getPatient_id(), bloodSugar.getDate(),
				bloodSugar.getSugar_value(), bloodSugar.getState(),
				bloodSugar.isMedicine(), bloodSugar.isInsulin(),
				bloodSugar.getOther_medicine(), bloodSugar.getCarbohybrate(),
				bloodSugar.getExercise_time(), bloodSugar.getRemark());
		try {
			cassandra.getSession().execute(boundInsertQuery);
		} catch (DriverException e) {
			throw e;
		}
		log.infof("save blood_sugar for patient_id #%s successfully!",
				bloodSugar.getPatient_id());
	}

	/**
	 * 根据起始时间、结束时间获取list数据
	 * 
	 * @param patient_id
	 * @param date
	 * @param date2
	 * @param state
	 * @return
	 */
	public Iterator<BloodSugar> findByPatientIdAndStartAndEnd(int patient_id,
			Date date, Date date2, String state) {
		BoundStatement boundInsertQuery = new BoundStatement(
				findByPatientIdAndStartAndEndAndState);
		boundInsertQuery.bind(patient_id, date, date2, state);
		ResultSet resultSet = cassandra.getSession().execute(boundInsertQuery);
		return new ResultIterator<BloodSugar>(resultSet, this);
	}

	/**
	 * 获取离时间最近的测量
	 * @param patientId
	 * @param date
	 * @param state
	 * @return
	 */
	public Iterator<BloodSugar> findLastByPatientIdAndStateAndDate(int patientId,
			Date date, int limit) {
		BoundStatement boundInsertQuery = new BoundStatement(
				findLastByPatientIdAndStateAndDate);
		boundInsertQuery.bind(patientId, date,limit);
		ResultSet resultSet = cassandra.getSession().execute(boundInsertQuery);
		return new ResultIterator<BloodSugar>(resultSet, this);
	}

		/**
	 * 获取病人第一条数据
	 * 
	 * @param patientId
	 * @return
	 */
	public BloodSugar findByPatientIdLimitOne(int patientId) {
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
