package com.mlnx.chronic.mapper;

import java.util.List;

import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.vo.DocVo;

public interface TUserDocMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserDoc record);

    TUserDoc selectByPrimaryKey(Integer id);

    List<TUserDoc> selectAll();

    int updateByPrimaryKey(TUserDoc record);

    TUserDoc findByRegistPhone(String phone);

	List<DocVo> findAllDoc();

	DocVo findDocById(Integer id);
}