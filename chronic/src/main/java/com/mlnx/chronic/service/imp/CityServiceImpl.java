package com.mlnx.chronic.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TCity;
import com.mlnx.chronic.mapper.TCityMapper;
import com.mlnx.chronic.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private TCityMapper tCityMapper;

	@Override
	public Page<TCity> list(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(TCity patient) {
		// TODO Auto-generated method stub

	}

	@Override
	public TCity get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TCity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TCity> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TCity> findByProvinceId(Integer id) {
		return tCityMapper.findByProvinceId(id);
	}

	@Override
	public void update(TCity city) {
		// TODO Auto-generated method stub

	}

	@Override
	public TCity findByUName(String cityName) {
		// TODO Auto-generated method stub
		return null;
	}

}
