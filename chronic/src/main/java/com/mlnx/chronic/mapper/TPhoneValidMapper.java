package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TPhoneValid;

public interface TPhoneValidMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_phone_valid
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_phone_valid
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    int insert(TPhoneValid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_phone_valid
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    TPhoneValid selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_phone_valid
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    List<TPhoneValid> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_phone_valid
     *
     * @mbggenerated Thu Nov 12 15:45:41 CST 2015
     */
    int updateByPrimaryKey(TPhoneValid record);
}