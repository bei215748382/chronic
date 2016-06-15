package com.mlnx.chronic.service.imp;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlnx.chronic.mapper.TPatientMedicineMapper;
import com.mlnx.chronic.mapper.TestBase;

public class MedicineServiceImplTest extends TestBase {
    
    @Autowired
    private TPatientMedicineMapper tPatientMedicineMapper;

    @Test
    public void testRegist() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testFindAll() {
        fail("Not yet implemented");
    }

    @Test
    public void testSettingDinner() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMedicine() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetMedicinePrescription() {
        System.out.println(tPatientMedicineMapper.getMedicinePrescription(15));
    }

    @Test
    public void testGetMedicineHistory() {
        fail("Not yet implemented");
    }

    @Test
    public void testSaveTakeMedicine() {
        fail("Not yet implemented");
    }

}
