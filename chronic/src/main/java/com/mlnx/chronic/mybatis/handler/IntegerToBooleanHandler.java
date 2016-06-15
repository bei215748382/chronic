
/**
 * @author bwh<bruce.bei@nbmlnx.com>
 * 2016年5月16日
 *
 */
package com.mlnx.chronic.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes({Boolean.class })
@MappedJdbcTypes({JdbcType.INTEGER})
public class IntegerToBooleanHandler implements TypeHandler<Boolean> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Boolean parameter,
            JdbcType jdbcType) throws SQLException {
        if(parameter){
            ps.setInt(i, 1);//true就设置为1
        } else{
            ps.setInt(i,2);//false设置为2，不用0的原因是mybatis在判断的时候，将0判断为null，就不会执行更新操作
        }
        
    }

    @Override
    public Boolean getResult(ResultSet rs, String columnName)
            throws SQLException {
        int value = rs.getInt(columnName);
        if(value == 1){
            return true;
        }
        return false;
    }

    @Override
    public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Boolean getResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

}