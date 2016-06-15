package com.mlnx.chronic.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.mlnx.chronic.vo.medicine.EatStatus;

@MappedTypes({EatStatus[].class })
@MappedJdbcTypes({ JdbcType.VARCHAR })
public class VarcharToEatStatusHandler implements TypeHandler<EatStatus[]> {

	@Override
	public void setParameter(PreparedStatement ps, int i, EatStatus[] parameter,
			JdbcType jdbcType) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for(int j = 0;i<parameter.length-1;i++){
			sb.append(parameter[j]+",");
		}
		sb.append(parameter[parameter.length]);
		ps.setString(i, sb.toString());
	}

	@Override
	public EatStatus[] getResult(ResultSet rs, String columnName)
			throws SQLException {
	    try{
	        String strs = rs.getString(columnName);
	        if(strs!=null&strs!=""){
	            String[] str = strs.split(",");
	            EatStatus[] eatStatus = new EatStatus[str.length];
	            for(int i = 0;i < str.length;i++){
	                eatStatus[i] = EatStatus.valueOf(str[i]);
	            }
	            return eatStatus;
	        }
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	    return new EatStatus[]{EatStatus.BEFORE_BREAK_FAST};
	}

	@Override
	public EatStatus[] getResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EatStatus[] getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


}
