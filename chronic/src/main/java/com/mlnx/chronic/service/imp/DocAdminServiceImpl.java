package com.mlnx.chronic.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mlnx.chronic.entity.TDevice;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.exception.TransactionalException;
import com.mlnx.chronic.mapper.TDeviceMapper;
import com.mlnx.chronic.mapper.TServiceDeviceMapper;
import com.mlnx.chronic.mapper.TServiceMapper;
import com.mlnx.chronic.mapper.TUserDocMapper;
import com.mlnx.chronic.mapper.TUserExtMapper;
import com.mlnx.chronic.mapper.TUserMapper;
import com.mlnx.chronic.mapper.TVoipAccountMapper;
import com.mlnx.chronic.service.DocAdminService;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.util.RegistVoip;
import com.mlnx.chronic.util.EnumCollection.ResponseCode;
import com.mlnx.chronic.vo.DeviceVo;
import com.mlnx.chronic.vo.DocVo;
import com.mlnx.chronic.vo.PatientVo;
import com.mlnx.chronic.vo.ServiceAddressData;
import com.mlnx.chronic.vo.ServiceData;
import com.mlnx.chronic.vo.ServiceDeviceData;
import com.mlnx.chronic.vo.ServiceVo;

@Service
public class DocAdminServiceImpl implements DocAdminService {

	@Autowired
	private TUserDocMapper tUserDocMapper;

	@Autowired
	private TUserMapper tUserMapper;

	@Autowired
	private TServiceMapper tServiceMapper;

	@Autowired
	private TUserExtMapper tUserExtMapper;

	@Autowired
	private TServiceDeviceMapper tServiceDeviceMapper;

	@Autowired
	private TDeviceMapper tDeviceMapper;
	
	@Autowired
	private TVoipAccountMapper tVoipAccountMapper;

	@Transactional(rollbackFor = TransactionalException.class)
	@Override
	public ChronicResponse regist(TUserDoc doc, TUser user) throws Exception {
		try {
			TUser tUser = tUserMapper.selectByPhone(user.getPhone());
			int userId = 0;
			if(tUser == null){
				tUserMapper.insert(user);
				doc.setUserId(user.getId());
				userId= user.getId();
			} else {
				doc.setUserId(tUser.getId());
				userId = tUser.getId();
			}
			tUserDocMapper.insert(doc);
			RegistVoip.regist(userId, null, tVoipAccountMapper);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionalException();
		}
		return new ChronicResponse(ResponseCode.REGISTER_DOC_SUCCESS);
	}

	@Transactional(rollbackFor = TransactionalException.class)
	@Override
	public ChronicResponse update(TUserDoc doc, TUser user) throws Exception {
		try {
			tUserMapper.updateByPrimaryKey(user);
			tUserDocMapper.updateByPrimaryKey(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionalException();
		}
		return new ChronicResponse(ResponseCode.UPDATE_DOC_SUCCESS);
	}

	@Override
	public List<TUserDoc> findAll() {
		return tUserDocMapper.selectAll();
	}

	@Override
	public TUserDoc findById(int id) {
		return tUserDocMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ServiceVo> findAllService() {
		return tServiceMapper.selectAllService();
	}

	@Override
	public List<ServiceData> findAllServiceGroupByDoctorId() {
		return tServiceMapper.findAllServiceGroupByDoctorId();
	}

	@Override
	public List<ServiceAddressData> findAllServiceGroupByAddressId() {
		return tServiceMapper.findAllServiceGroupByAddressId();
	}

	@Override
	public List<PatientVo> getPatients(int id) {
		return tUserExtMapper.getPatients(id);
	}

	@Override
	public TUserDoc findByRegistPhone(String phone) {
		return tUserDocMapper.findByRegistPhone(phone);
	}

	@Override
	public List<DocVo> findAllDoc() {
		return tUserDocMapper.findAllDoc();
	}

	@Override
	public DocVo findDocById(Integer id) {
		return tUserDocMapper.findDocById(id);
	}

	@Override
	public ChronicResponse updateDoc(TUserDoc doc) {
		tUserDocMapper.updateByPrimaryKey(doc);
		return null;
	}

	@Override
	public List<ServiceDeviceData> findServiceByDeviceId() {
		return tServiceDeviceMapper.findServiceByDeviceId();
	}

	@Override
	public DeviceVo findDeviceById(Integer id) {
		return tDeviceMapper.findDeviceById(id);
	}

	@Override
	public List<TDevice> findAllDevice() {
		return tDeviceMapper.selectAll();
	}

	@Override
	public List<TUserExt> findAllPatients() {
		return tUserExtMapper.selectAll();
	}

	@Override
	public void addDevice(TDevice device) {
		tDeviceMapper.insert(device);
	}

	@Override
	public void updateDeviceById(TDevice device) {
		tDeviceMapper.updateByPrimaryKey(device);
		
	}

	@Override
	public void deleteDeviceById(Integer id) {
		tDeviceMapper.deleteByPrimaryKey(id);
	}

}
