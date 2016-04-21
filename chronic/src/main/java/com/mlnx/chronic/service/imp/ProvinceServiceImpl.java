package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TProvince;
import com.mlnx.chronic.mapper.TProvinceMapper;
import com.mlnx.chronic.service.ProvinceService;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
@Service
public class ProvinceServiceImpl implements ProvinceService {
	
	@Autowired
	private TProvinceMapper tProvinceMapper;

	@Override
	public Page<TProvince> list(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(TProvince province) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TProvince get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TProvince> findAll() {
		return tProvinceMapper.selectAll();
	}

	@Override
	public List<TProvince> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(TProvince province) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TProvince findByUName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findAllProvince() {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<TProvince> list = tProvinceMapper.selectAll();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_PROVINCE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_PROVINCE_SUCCESS.getCode());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_PROVINCE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_PROVINCE_ERROR.getCode());
		}
		return map;
	}

}
