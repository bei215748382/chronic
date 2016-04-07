package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;










import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mlnx.chronic.entity.TVisit;
import com.mlnx.chronic.entity.TVisitReport;
import com.mlnx.chronic.entity.TVisitTest;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.mapper.TVisitMapper;
import com.mlnx.chronic.mapper.TVisitReportMapper;
import com.mlnx.chronic.mapper.TVisitTestMapper;
import com.mlnx.chronic.service.VisitService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.vo.VisitExt;
import com.mlnx.chronic.vo.VisitVo;

@Service
public class VisitServiceImpl implements VisitService {

	@Autowired
	private TVisitMapper tVisitMapper;

	@Autowired
	private TVisitTestMapper tVisitTestMapper;
	
	@Autowired
	private TVisitReportMapper tVisitReportMapper;

	private TVisit toVisit(VisitExt visitExt) {
		TVisit visit = new TVisit();
		visit.setId(visitExt.getId());
		visit.setDoctorId(visitExt.getDoctorId());
		visit.setPatientId(visitExt.getPatientId());
		visit.setDate(visitExt.getDate());
		visit.setState(visitExt.getState());
		return visit;
	}

	@Transactional(rollbackFor=TransactionalException.class)
	@Override
	public ChronicResponse regist(TVisit visit, List<Integer> tests)
			throws TransactionalException {
		try {
			tVisitMapper.insert(visit);
			int id = visit.getId();
			for (int i = 0; i < tests.size(); i++) {
				TVisitTest vt = new TVisitTest();
				vt.setVisitId(id);
				vt.setTestId(tests.get(i));
				tVisitTestMapper.insert(vt);
			}
			return new ChronicResponse(ResponseCode.REGISTER_VISIT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionalException();
		}
	}

	@Override
	public ChronicResponse edit(TVisit visit) {
		try {
			tVisitMapper.updateByPrimaryKey(visit);
			return new ChronicResponse(ResponseCode.UPDATE_VISIT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.UPDATE_VISIT_ERROR);
		}
	}

	@Override
	public ChronicResponse delete(int id) {
		try {
			tVisitMapper.deleteByPrimaryKey(id);
			return new ChronicResponse(ResponseCode.DELETE_VISIT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.DELETE_VISIT_ERROR);
		}
	}

	@Override
	public Map<String, Object> findAllByDoctorId(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<TVisit> visits = tVisitMapper.selectAllByDoctorId(id);
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_VISIT_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_VISIT_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, visits);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_VISIT_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_VISIT_ERROR.getMsg());
			return map;
		}

	}
	
	@Transactional(rollbackFor=TransactionalException.class)
	@Override
	public ChronicResponse edit(VisitExt visitExt) throws TransactionalException {
		int visitId = visitExt.getId();
		TVisit visit = toVisit(visitExt);
		try {
			tVisitMapper.updateByPrimaryKey(visit);
			List<Integer> ids = visitExt.getTests();
			if(ids!=null&&ids.size()!=0){
				for(int i = 0;i<ids.size();i++){
					TVisitTest vt = new TVisitTest();
					vt.setVisitId(visitId);
					vt.setTestId(ids.get(i));
					tVisitTestMapper.insert(vt);
				}
			}
			return new ChronicResponse(ResponseCode.UPDATE_VISIT_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionalException();
		}
	}


	@Override
	public Map<String, Object> search(Integer doctorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<VisitVo> visits = tVisitMapper.search(doctorId);
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_VISIT_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_VISIT_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, visits);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_VISIT_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_VISIT_ERROR.getMsg());
			return map;
		}
	}

	@Override
	public Map<String, Object> searchHistory(Integer doctorId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<VisitVo> visits = tVisitMapper.searchHistory(doctorId);
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_VISIT_HISTORY_SUCCESS.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_VISIT_HISTORY_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, visits);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put(StringUtil.responseCode,
					ResponseCode.SEARCH_VISIT_HISTORY_ERROR.getCode());
			map.put(StringUtil.responseMsg,
					ResponseCode.SEARCH_VISIT_HISTORY_ERROR.getMsg());
			return map;
		}
	}

	@Override
	public ChronicResponse addReport(int visitId,String condition, String medicine, CommonsMultipartFile[] files, HttpServletRequest request) {
		if(files!=null && files.length>0){
			//TODO save files
		}
		TVisitReport tVr = new TVisitReport();
		tVr.setVisitId(visitId);
		tVr.setConditiona(condition);
		tVr.setMedicine(medicine);
		try{
			tVisitReportMapper.insert(tVr);
			return new ChronicResponse(ResponseCode.ADD_VISIT_REPORT_SUCCESS);
		} catch(Exception e){
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.ADD_VISIT_REPORT_ERROR);
		}
	}

}
