package com.mlnx.chronic.mapper;

import com.mlnx.chronic.entity.TPatientMedicine;
import com.mlnx.chronic.vo.medicine.MedicinePrescription;

import java.util.List;

public interface TPatientMedicineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPatientMedicine record);

    TPatientMedicine selectByPrimaryKey(Integer id);

    List<TPatientMedicine> selectAll();

    int updateByPrimaryKey(TPatientMedicine record);

	List<MedicinePrescription> getMedicinePrescription(Integer id);
}