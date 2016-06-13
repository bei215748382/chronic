package com.mlnx.chronic.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static String accountSid = "aaf98f894e52805a014e717a21831cfb"; // 贝的aaf98f894e52805a014e717a21831cfb  舒乐 aaf98f894ecd7d6a014ed7a711d40f95
	public static String accountToken = "13194fa7d1d14ef48c7d92f31e20f1dc";// 贝的13194fa7d1d14ef48c7d92f31e20f1dc 舒乐 6b1136007498420e9ae218b4843fd020
	public static String appId = "8a48b5514e5298b9014e717cc28e1f22";// 贝的8a48b5514e5298b9014e717cc28e1f22 舒乐 aaf98f894fd44d15014fda6ffe770797

	public static String friendConfirmerror = "好友关系确认失败，事务将回滚";
	public static String responseCode = "responseCode";
	public static String responseMsg = "msg";
	public static String responseObj = "obj";
	public static String responseObjList = "objList";

	public static final String patientName = "姓名";

	public static final String docAdminLogin = "docAdminLogin";

	public static final String adminLogin = "adminLogin";

	public static final Integer LOGIN_PERMISSION = 1;

	public static final Integer LOGIN_DOC_PERMISSION = 2;

	public static final Integer LOGIN_ADMIN_PERMISSION = 3;

	public static final String LOGIN_NO_PERMISSION = "对不起，您没有登入权限！请联系管理员开通！";

	public static final String LOGIN_PASSWORD_ERROR = "对不起，登入用户密码错误！";

	public static final String BASE_URL = "http://121.40.137.14";

	public static final String BASE_PORT = "80";

	public static final String BASE_ROOT_PATH = "doc";

	public static final String BASE_BACK_URL = "http://localhost";

	public static final String BASE_BACK_PORT = "8080";

	public static final String BASE_BACK_ROOT_PATH = "chronic";
	
	public static String stringArrayToString(List<String> strs){
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i<strs.size()-1;i++){
			sb.append(strs.get(i)+",");
		}
		sb.append(strs.get(strs.size()-1));
		return sb.toString();
	}
	
	public static List<String> stringToStringArray(String str){
		List<String> strArr = new ArrayList<String>();
		if(str!=null){
			String[] strs = str.split(",");
			for(String st : strs){
				strArr.add(st);
			}
		}
		return strArr;
	}
}
