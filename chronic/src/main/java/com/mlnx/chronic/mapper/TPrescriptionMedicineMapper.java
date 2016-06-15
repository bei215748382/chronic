package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TPrescriptionMedicine;
import java.util.List;

public interface TPrescriptionMedicineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPrescriptionMedicine record);

    TPrescriptionMedicine selectByPrimaryKey(Integer id);

    List<TPrescriptionMedicine> selectAll();

    int updateByPrimaryKey(TPrescriptionMedicine record);
}