package com.mlnx.chronic.util;

import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.mlnx.chronic.entity.TVoipAccount;
import com.mlnx.chronic.exception.RegisterException;
import com.mlnx.chronic.mapper.TVoipAccountMapper;

/**
 * 用于注册容联云子账号
 * 
 * @author bwh
 * 
 */
public class RegistVoip {

	private static final Logger log = LoggerFactory.getLogger(RegistVoip.class);

	public static ChronicResponse regist(int userId, String friendName,TVoipAccountMapper tVoipAccountMapper) throws RegisterException {
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount(StringUtil.accountSid, StringUtil.accountToken);// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId(StringUtil.appId);// 初始化应用ID
		result = restAPI.createSubAccount(friendName);
		log.info("SDKTestCreateSubAccount result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			// 正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result
					.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				log.info(key + " = " + object);
				String[] s = object.toString().split(",");
				String[] subAccountSidStr = s[0].split("=");
				String[] voipAccountStr = s[1].split("=");
				String[] dateCreatedStr = s[2].split("=");
				String[] voipPwdtStr = s[3].split("=");
				String[] subTokenStr = s[4].split("=");
				TVoipAccount tv = new TVoipAccount();
				tv.setVoipAccount(voipAccountStr[1]);
				tv.setVoipPassword(voipPwdtStr[1]);
				tv.setSubAccountSid(subAccountSidStr[1]);
				tv.setSubToken(subTokenStr[1]);
				tv.setDateCreated(dateCreatedStr[1]);
				tv.setFriendName(friendName);
				tv.setUserId(userId);
				tVoipAccountMapper.insert(tv);
			}
		} else {
			// 异常返回输出错误码和错误信息
			log.info("错误码=" + result.get("statusCode") + " 错误信息= "
					+ result.get("statusMsg"));
			throw new RegisterException(result.get("statusMsg"));
		}
		return null;
	}
}
