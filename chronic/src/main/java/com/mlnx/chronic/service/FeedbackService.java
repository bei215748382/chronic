package com.mlnx.chronic.service;

import java.util.List;

import com.mlnx.chronic.entity.TFeedback;
import com.mlnx.chronic.util.ChronicResponse;

public interface FeedbackService {

	// 提交反馈
	public ChronicResponse registFeedback(TFeedback feedback);

	// 查找所有
	public List<TFeedback> selectAll();

	// 根据主键更改
	public ChronicResponse updateByPrimaryKey(TFeedback tfb);

	// 根据主键查找问题
	public TFeedback selectByPrimaryKey(Integer id);

	// 根据主键删除
	public ChronicResponse deleteByPrimaryKey(Integer id);
}
