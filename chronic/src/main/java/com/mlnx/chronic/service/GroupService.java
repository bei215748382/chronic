package com.mlnx.chronic.service;

import java.util.Map;

import com.mlnx.chronic.entity.TGroup;
import com.mlnx.chronic.util.ChronicResponse;

public interface GroupService {

	// 添加群组
	public Map<String, Object> regist(TGroup group);

	// 编辑群组
	public ChronicResponse edit(TGroup group);

	// 删除群组
	public ChronicResponse delete(int id);
}
