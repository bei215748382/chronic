package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TRemind;

public interface TRemindMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_remind
     *
     * @mbggenerated Tue Dec 15 16:50:12 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_remind
     *
     * @mbggenerated Tue Dec 15 16:50:12 CST 2015
     */
    int insert(TRemind record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_remind
     *
     * @mbggenerated Tue Dec 15 16:50:12 CST 2015
     */
    TRemind selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_remind
     *
     * @mbggenerated Tue Dec 15 16:50:12 CST 2015
     */
    List<TRemind> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_remind
     *
     * @mbggenerated Tue Dec 15 16:50:12 CST 2015
     */
    int updateByPrimaryKey(TRemind record);
}