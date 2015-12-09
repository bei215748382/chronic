package com.mlnx.chronic.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes({ Long.class })
@MappedJdbcTypes({ JdbcType.TIMESTAMP })
public class DateToLongHandler implements TypeHandler<Long> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Long parameter,
			JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Long getResult(ResultSet rs, String columnName) throws SQLException {
		Date date = rs.getDate(columnName);
		if (date == null) {
			return null;
		} else {
			return date.getTime();
		}
	}

	@Override
	public Long getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("columnIndex:" + columnIndex);
		return null;
	}

	@Override
	public Long getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
