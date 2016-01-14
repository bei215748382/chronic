package com.mlnx.chronic.repo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.exceptions.DriverException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.mlnx.chronic.entity.Phone;
import com.mlnx.chronic.service.CassandraService;
import com.mlnx.chronic.util.ResultTransformer;

public class PhoneRepository implements ResultTransformer<Phone> {

	private static final String TABLE_NAME = "phone";

	private static enum Column {

		PHONE("phone", DataType.text()), CODE("code", DataType.cint()), UPDATE_TIME(
				"update_time", DataType.timestamp());

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
			+ String.format("  %s PRIMARY KEY,\n", Column.PHONE)
			+ String.format("  %s,\n", Column.CODE)
			+ String.format("  %s\n", Column.UPDATE_TIME) + ")";

	private static final String INSERT_CQL = String.format(
			"INSERT INTO %s (\n", TABLE_NAME)
			+ String.format("  %s,\n", Column.PHONE.name)
			+ String.format("  %s,\n", Column.CODE.name)
			+ String.format("  %s\n", Column.UPDATE_TIME.name)
			+ ") VALUES (\n"
			+ CassandraService
					.generateValuePlaceholders(Column.values().length) + "\n)";

	private static final Select FIND_ALL_QUERY = QueryBuilder.select().all()
			.from(TABLE_NAME);

	private static final String FIND_BY_ID_CQL = String.format(
			"SELECT * FROM %s", TABLE_NAME)
			+ String.format(" WHERE %s = ?", Column.PHONE.name);

	private static final String DELETE_CQL = String.format("DELETE FROM %s",
			TABLE_NAME) + String.format(" WHERE %s = ?", Column.PHONE.name);

	private static final Logger log = LoggerFactory
			.getLogger(PhoneRepository.class);

	@Autowired
	private CassandraService cassandra;

	private PreparedStatement insertQuery;

	private PreparedStatement findByIdQuery;

	private PreparedStatement deleteQuery;

	@PostConstruct
	private void init() throws Exception {

		cassandra.createTableIfNotExists(TABLE_NAME, CREATE_TABLE_CQL);
		insertQuery = cassandra.getSession().prepare(INSERT_CQL);
		findByIdQuery = cassandra.getSession().prepare(FIND_BY_ID_CQL);
		deleteQuery = cassandra.getSession().prepare(DELETE_CQL);
	}

	@Override
	public Phone transform(Row row) {

		Phone phone = new Phone();
		phone.setPhone(row.getString(Column.PHONE.name));
		phone.setCode(row.getInt(Column.CODE.name));
		phone.setTime(row.getDate(Column.UPDATE_TIME.name));
		return phone;
	}

	public Phone findById(String phone) {

		BoundStatement boundFindByIdQuery = new BoundStatement(findByIdQuery);
		boundFindByIdQuery.bind(phone);
		ResultSet resultSet = cassandra.getSession()
				.execute(boundFindByIdQuery);
		Row result = resultSet.one();
		return result == null ? new Phone() : transform(result);
	}

	public List<Phone> findAllOrderedById() {

		ResultSet resultSet = cassandra.getSession().execute(FIND_ALL_QUERY);
		List<Phone> phones = new ArrayList<Phone>();
		for (Row result : resultSet) {
			Phone phone = transform(result);
			phones.add(phone);
		}
		return phones;
	}

	/**
	 * 保存用户信息
	 * 
	 * @param phone
	 */
	public void save(Phone phone) {

		BoundStatement boundInsertQuery = new BoundStatement(insertQuery);
		boundInsertQuery.bind(phone.getPhone(), phone.getCode(),
				phone.getTime());
		try {
			cassandra.getSession().execute(boundInsertQuery);
		} catch (DriverException e) {
			log.error(String.format(
					"Failed to create/update database entry for Phone %s",
					phone.getPhone()));
			throw e;
		}
		log.info(String.format("Created/updated database entry for Phone %s ",
				phone.getPhone()));
	}

	/**
	 * 删除手机号
	 * 
	 * @param phone
	 */
	public void delete(Phone phone) {

		BoundStatement boundDeleteQuery = new BoundStatement(deleteQuery);
		boundDeleteQuery.bind(phone.getPhone());
		try {
			cassandra.getSession().execute(boundDeleteQuery);
		} catch (DriverException e) {
			log.error(String.format(
					"Failed to delete database entry for phone %s",
					phone.getPhone()));
			throw e;
		}
		log.info(String.format("Deleted database entry for phone %s",
				phone.getPhone()));
	}
}
