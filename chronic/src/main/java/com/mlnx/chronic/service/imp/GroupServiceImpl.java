package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TGroup;
import com.mlnx.chronic.mapper.TGroupMapper;
import com.mlnx.chronic.service.GroupService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private TGroupMapper tGroupMapper;

	@Override
	public Map<String,Object> regist(TGroup group) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			tGroupMapper.insert(group);
			map.put(StringUtil.responseCode, ResponseCode.ADD_GROUP_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.ADD_GROUP_SUCCESS.getMsg());
			map.put(StringUtil.responseObj, group);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.ADD_GROUP_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.ADD_GROUP_ERROR.getMsg());
			return map;
		}
	}

	@Override
	public ChronicResponse edit(TGroup group) {
		try {
			tGroupMapper.updateByPrimaryKey(group);
			return new ChronicResponse(ResponseCode.UPDATE_GROUP_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.UPDATE_GROUP_ERROR);
		}
	}

	@Override
	public ChronicResponse delete(int id) {
		try {
			tGroupMapper.deleteByPrimaryKey(id);
			return new ChronicResponse(ResponseCode.DELETE_GROUP_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.DELETE_GROUP_ERROR);
		}
	}

	@Override
	public Map<String,Object> searchGroup(Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<TGroup> list = tGroupMapper.searchGroup(id);
			map.put(StringUtil.responseCode, ResponseCode.SEARCH_GROUP_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.SEARCH_GROUP_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.SEARCH_GROUP_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.SEARCH_GROUP_ERROR.getMsg());
			return map;
		}
	}

}
