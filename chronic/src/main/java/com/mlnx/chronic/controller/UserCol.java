package com.mlnx.chronic.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mlnx.chronic.entity.Phone;
import com.mlnx.chronic.entity.TBloodPressureSetting;
import com.mlnx.chronic.entity.TBloodSugarSetting;
import com.mlnx.chronic.entity.TUser;
import com.mlnx.chronic.repo.PhoneRepository;
import com.mlnx.springmvc.service.UserService;
import com.mlnx.springmvc.util.ChronicResponse;
import com.mlnx.springmvc.util.StringUtil;

@Controller
@RequestMapping(value = "/user")
public class UserCol {

	@Autowired
	private UserService userService;

	private PhoneRepository phoneRespository;

	/**
	 * 手机端注册用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "regist")
	@ResponseBody
	public ChronicResponse regist(TUser user) throws Exception {
		return userService.registUser(user);
	}
	

	/**
	 * 手机验证码注册用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "registByValidcode")
	@ResponseBody
	public ChronicResponse registByValidcode(TUser user,Integer code) throws Exception {
		return userService.registUser(user,code);
	}

	/**
	 * 获取手机验证码
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping(value = "getCode/{phone}")
	@ResponseBody
	public boolean sendCode(@PathVariable String phone) {
		HashMap<String, Object> result = null;
		// 初始化SDK
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

		// ******************************注释*********************************************
		// *初始化服务器地址和端口 *
		// *沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		// *生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883"); *
		// *******************************************************************************
		restAPI.init("sandboxapp.cloopen.com", "8883");

		// ******************************注释*********************************************
		// *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
		// *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		// *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
		// *******************************************************************************
		restAPI.setAccount(StringUtil.accountSid,
				StringUtil.accountToken);

		// ******************************注释*********************************************
		// *初始化应用ID *
		// *测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID *
		// *应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		// *******************************************************************************
		restAPI.setAppId(StringUtil.appId);

		// ******************************注释****************************************************************
		// *调用发送模板短信的接口发送短信 *
		// *参数顺序说明： *
		// *第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号 *
		// *第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。 *
		// *系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		// *第三个参数是要替换的内容数组。 *
		// **************************************************************************************************

		// **************************************举例说明***********************************************************************
		// *假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为
		// *
		// *result = restAPI.sendTemplateSMS("13800000000","1" ,new
		// String[]{"6532","5"}); *
		// *则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入 *
		// *********************************************************************************************************************
		int code = (int) (Math.random() * 1000000);
		result = restAPI.sendTemplateSMS(phone, "1",
				new String[] { String.valueOf(code), "1" });

		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
		} else {
			// 异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
		}

		Phone ph = new Phone();
		ph.setPhone(phone);
		ph.setCode(code);
		ph.setTime(new Date());
		phoneRespository.save(ph);

		return true;
	}

	/**
	 * 驗證手機驗證碼
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "validCode/{phone}/{code}")
	@ResponseBody
	public boolean valid(@PathVariable("phone") String phone,
			@PathVariable("code") int code) {
		Phone ph = phoneRespository.findById(phone);
		if (code != ph.getCode()) {
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "login/{phone}/{password}")
	@ResponseBody
	public ChronicResponse login(@PathVariable("phone") String phone,
			@PathVariable("password") String password) throws Exception {
		return userService.login(phone, password);
	}
	
	@RequestMapping(value = "bloodPressureSetting")
	@ResponseBody
	public ChronicResponse bloodPressureSetting(TBloodPressureSetting bloodPressureSetting){
		return userService.updateBloodPressureSetting(bloodPressureSetting);
	}
	
	@RequestMapping(value = "bloodSugarSetting")
	@ResponseBody
	public ChronicResponse bloodSugarSetting(TBloodSugarSetting bloodSugarSetting){
		return userService.updateBloodSugarSetting(bloodSugarSetting);
	}
}
