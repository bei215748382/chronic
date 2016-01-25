package com.mlnx.chronic.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.THospital;
import com.mlnx.chronic.mapper.THospitalMapper;
import com.mlnx.chronic.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private THospitalMapper tHospitalMapper;

	@Override
	public Page<THospital> list(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(THospital patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public THospital get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<THospital> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<THospital> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<THospital> findByCityId(Integer id) {
		return tHospitalMapper.findByCityId(id);
	}

	@Override
	public void update(THospital hospital) {
		// TODO Auto-generated method stub

	}

	@Override
	public THospital findByUName(String hospitalName) {
		// TODO Auto-generated method stub
		return null;
	}

}
