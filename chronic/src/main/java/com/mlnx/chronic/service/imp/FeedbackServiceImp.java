package com.mlnx.chronic.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TFeedback;
import com.mlnx.chronic.mapper.TFeedbackMapper;
import com.mlnx.chronic.service.FeedbackService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;

@Service
public class FeedbackServiceImp implements FeedbackService {

	@Autowired
	private TFeedbackMapper tFeedbackMapper;

	@Override
	public ChronicResponse registFeedback(TFeedback feedback) {
		try {
			tFeedbackMapper.insert(feedback);
			return new ChronicResponse(
					EnumCollection.ResponseCode.ADD_FEEDBACK_SUCCESS);
		} catch (Exception e) {
			return new ChronicResponse(ResponseCode.ADD_FEEDBACK_ERROR);
		}
	}

	@Override
	public List<TFeedback> selectAll() {
		return tFeedbackMapper.selectAll();
	}

	@Override
	public ChronicResponse updateByPrimaryKey(TFeedback tfb) {
		tFeedbackMapper.updateByPrimaryKey(tfb);
		return null;
	}

	@Override
	public TFeedback selectByPrimaryKey(Integer id) {
		return tFeedbackMapper.selectByPrimaryKey(id);
	}

	@Override
	public ChronicResponse deleteByPrimaryKey(Integer id) {
		tFeedbackMapper.deleteByPrimaryKey(id);
		return null;
	}

}
