package com.mlnx.chronic.service;

import java.util.List;

import com.mlnx.chronic.entity.TDevice;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.entity.TUserDoc;
import com.mlnx.chronic.entity.TUserExt;
import com.mlnx.chronic.util.ChronicResponse;
import com.mlnx.chronic.vo.DeviceVo;
import com.mlnx.chronic.vo.DocVo;
import com.mlnx.chronic.vo.PatientVo;
import com.mlnx.chronic.vo.ServiceAddressData;
import com.mlnx.chronic.vo.ServiceData;
import com.mlnx.chronic.vo.ServiceDeviceData;
import com.mlnx.chronic.vo.ServiceVo;

public interface DocAdminService {

	// 注册医生
	public ChronicResponse regist(TUserDoc doc, TUser user) throws Exception;

	// 修改医生
	public ChronicResponse update(TUserDoc doc, TUser user) throws Exception;
	
	// 修改医生
	public ChronicResponse updateDoc(TUserDoc doc) ;

	// 查找医生信息
	public List<TUserDoc> findAll();

	// 根据id查找医生信息
	public TUserDoc findById(int id);

	// 查找所有服务信息
	public List<ServiceVo> findAllService();

	public List<ServiceData> findAllServiceGroupByDoctorId();

	public List<ServiceAddressData> findAllServiceGroupByAddressId();

	public List<PatientVo> getPatients(int id);

	// 查看手机号是否被医生账户注册
	public TUserDoc findByRegistPhone(String phone);

	public List<DocVo> findAllDoc();

	public DocVo findDocById(Integer id);

	public List<ServiceDeviceData> findServiceByDeviceId();

	public DeviceVo findDeviceById(Integer id);

	public List<TDevice> findAllDevice();

	public List<TUserExt> findAllPatients();

	public void addDevice(TDevice device);

	public void updateDeviceById(TDevice device);

	public void deleteDeviceById(Integer id);

}
