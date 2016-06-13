package com.mlnx.chronic.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlnx.chronic.entity.TCity;
import com.mlnx.chronic.entity.THospital;
import com.mlnx.chronic.entity.TProvince;
import com.mlnx.chronic.entity.TQuestion;
import com.mlnx.chronic.entity.TUserBook;
import com.mlnx.chronic.mapper.TCityMapper;
import com.mlnx.chronic.mapper.THospitalMapper;
import com.mlnx.chronic.mapper.TProvinceMapper;
import com.mlnx.chronic.mapper.TQuestionMapper;
import com.mlnx.chronic.mapper.TUserBookMapper;
import com.mlnx.chronic.mapper.TUserDocMapper;
import com.mlnx.chronic.service.InquiryService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.util.StringUtil;
import com.mlnx.chronic.vo.DocVo;
import com.mlnx.chronic.vo.UsrBook;

@Service
public class InquiryServiceImpl implements InquiryService {
	
	@Autowired
	private TProvinceMapper tProvinceMapper;
	
	@Autowired
	private TCityMapper tCityMapper;
	
	@Autowired
	private THospitalMapper tHospitalMapper;
	
	@Autowired
	private TUserDocMapper tUserDocMapper;
	
	@Autowired
	private TQuestionMapper tQuestionMapper;
	
	@Autowired
	private TUserBookMapper tUserBookMapper;

	@Override
	public Map<String, Object> findAllProvince() {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<TProvince> list = tProvinceMapper.selectAll();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_PROVINCE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_PROVINCE_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_PROVINCE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_PROVINCE_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> findAllCity(String id) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<TCity> list = tCityMapper.findByProvinceName(id);
			if(list == null || list.size()==0){
				list = tCityMapper.findByProvinceId(Integer.parseInt(id));
			}
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_CITY_BY_PROVINCE_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_CITY_BY_PROVINCE_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_CITY_BY_PROVINCE_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_CITY_BY_PROVINCE_ERROR.getMsg());
		}
		return map;
	}


	@Override
	public Map<String, Object> findAllHospitalWithMap(Map<String, Object> paramMap) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<THospital> list = tHospitalMapper.findByCityNameWithLevel(paramMap);
			if(list == null || list.size()==0){
				list = tHospitalMapper.findByCityIdWithLevel(paramMap);
			}
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_HOSPITAL_BY_CITY_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_HOSPITAL_BY_CITY_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_HOSPITAL_BY_CITY_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_HOSPITAL_BY_CITY_ERROR.getMsg());
		}
		return map;
	}



	@Override
	public Map<String, Object> findAllDocWithKey(String keyStr) {
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("keyStr", keyStr);
		try{
			List<DocVo> list = tUserDocMapper.findAllDocWithKey(paramMap);
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> findAllDocWithHospitalAndGroup(
			Map<String, Object> paramMap) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<DocVo> list = tUserDocMapper.findAllDocWithHospitalNameAndGroupId(paramMap);
			if(list == null || list.size()==0){
				list = tUserDocMapper.findAllDocWithHospitalIdAndGroupId(paramMap);
			}
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> findAllDocByCity(Map<String, Object> paramMap) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<DocVo> list = tUserDocMapper.findAllDocByCity(paramMap);
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.FIND_ALL_DOC_BY_HOSPITAL_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public ChronicResponse question(TQuestion question) {
		try{ 
			tQuestionMapper.insert(question);
			return new ChronicResponse(ResponseCode.INQUIRY_QUESTION_SUCCESS);
		} catch(Exception e){
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.INQUIRY_QUESTION_ERROR);
		}
	}

	@Override
	public Map<String, Object> historyQuestion(String keyword, Integer start,Integer end) {
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("keyword", keyword);
		paramMap.put("start", start);
		paramMap.put("end", end);
		try{
			List<TQuestion> list = tQuestionMapper.historyQuestion(paramMap);
			map.put(StringUtil.responseCode, ResponseCode.INQUIRY_HISTORY_QUESTION_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.INQUIRY_HISTORY_QUESTION_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.INQUIRY_HISTORY_QUESTION_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.INQUIRY_HISTORY_QUESTION_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public Map<String, Object> docHistoryQuestion(String id, Integer start,Integer end) {
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", id);
		paramMap.put("start", start);
		paramMap.put("end", end);
		try{
			List<TQuestion> list = tQuestionMapper.docHistoryQuestion(paramMap);
			map.put(StringUtil.responseCode, ResponseCode.INQUIRY_DOC_HISTORY_QUESTION_SUCCESS.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.INQUIRY_DOC_HISTORY_QUESTION_SUCCESS.getMsg());
			map.put(StringUtil.responseObjList, list);
		} catch(Exception e){
			e.printStackTrace();
			map.put(StringUtil.responseCode, ResponseCode.INQUIRY_DOC_HISTORY_QUESTION_ERROR.getCode());
			map.put(StringUtil.responseMsg, ResponseCode.INQUIRY_DOC_HISTORY_QUESTION_ERROR.getMsg());
		}
		return map;
	}

	@Override
	public ChronicResponse book(UsrBook usrBook) {
		try{
			TUserBook tub = usrBookToTUserBook(usrBook);
			tUserBookMapper.insert(tub);
			return new ChronicResponse(ResponseCode.INQUIRY_BOOK_SUCCESS);
		} catch(Exception e){
			e.printStackTrace();
			return new ChronicResponse(ResponseCode.INQUIRY_BOOK_ERROR);
		}
		 
	}

	private TUserBook usrBookToTUserBook(UsrBook usrBook) {
		String pics = StringUtil.stringArrayToString(usrBook.getPics());
		TUserBook tub = new TUserBook();
		tub.setDate(usrBook.getDate());
		tub.setDescribe(usrBook.getDescribe());
		tub.setDocId(usrBook.getDoctorId());
		tub.setPics(pics);
		tub.setStatus(usrBook.getBookStatuses().name());
		tub.setUserId(usrBook.getUsrId());
		return tub;
	}

}
