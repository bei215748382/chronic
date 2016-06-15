package com.mlnx.chronic.util;

public class EnumCollection {

	public enum ResponseCode {
		EXIST("000001", "注册用户已存在"), SUCCESS("000002", "用户注册成功"), PROVINCE_REGISTER_SUCCESS(
				"000003", "注册城市成功"), CITY_REGISTER_SUSSESS("000004", "注册城市成功"), HOSPITAL_REGISTER_SUSSESS(
				"000005", "注册医院成功"), PATIENT_REGISTER_SUSSESS("000006",
				"注册病人成功"), PATIENT_MODIFY_SUSSESS("000007", "修改病人成功"), ORDER_REGISTER_SUSSESS(
				"000008", "注册预约成功"), VOIP_EXIST("000009", "容联云申请子账户名称重复"), LOGIN_SUCCESS(
				"000010", "登入成功"), LOGIN_PASSWORD_ERROR("000011", "用户名密码错误"), LOGIN_USERNAME_NOT_EXIST(
				"000012", "用户名不存在"), ADD_FRIENDS_SUCCESS("000013", "添加好友成功"), PROVINCE_DELETE_SUCCESS(
				"000014", "删除省成功"), ORDER_REMIND_SUCCESS("000015", "设置提醒成功"), DOCTOR_MODIFY_SUCCESS(
				"000016", "修改医生信息成功"), UPDATE_BLOODPRESSURESETTING_SUCCESS(
				"000017", "修改血压设置成功"), UPDATE_BLOODSUGARSETTING_SUCCESS(
				"000018", "修改血糖设置成功"), REGISTER_VALIDCODE_ERROR("000019",
				"验证码验证失败"), USER_ADD_FRIEND_REQUEST("000020", "已成功发送添加好友请求"), USER_ADD_FRIEND_REQUEST_FAIL(
				"000021", "好友请求已发送过了"), ADD_FRIENDS_CANCEL("000022", "取消添加好友"), UPDATE_FRIEND_REMARK_SUCCESS(
				"000023", "修改好友备注成功"), ADD_PHONE_VALID_CODE_SUCCESS("000024",
				"获取手机验证码成功"), UPDATE_USER_PHONE_NOT_EXIST("000025", "修改的手机号不存在"), UPDATE_USER_PASSWORD_SUCCESS(
				"000026", "修改用户密码成功"), ADD_FRIENDS_SUCCESS_ALREADY("000027",
				"已经添加过好友"), ADD_FRIENDS_ERROR("000028", "添加好友失败"), NO_PERMISSION_OPEN(
				"000029", "对不起，对方设置了权限，无法查看"), PERMISSION_OPEN_ALLOWED(
				"000030", "对方的信息你能查看"), ADD_FEEDBACK_SUCCESS("000031",
				"提交反馈问题成功"), UPDATE_USER_EXT_SUCCESS("000032", "用户修改详细成功"), GET_CODE_ERROR(
				"000033", "获取验证码失败"), GET_CODE_SUCCESS("000034", "获取验证码成功"), UPDATE_USER_PASSWORD_ERROR(
				"000035", "修改用户密码失败"), UPDATE_USER_EXT_ERROR("000036",
				"修改用户信息失败"), UPLOAD_PIC_SUCCESS("000037", "上传头像成功"), UPLOAD_PIC_ERROR(
				"000038", "上传头像失败"), FIND_USER_INFO_SUCCESS("000039",
				"获取用户信息成功"), FIND_USER_INFO_ERROR("000040", "获取用户信息失败"), FIND_VOIP_ACCOUNT_SUCCESS(
				"000041", "用户voip信息查找成功"), FIND_VOIP_ACCOUNT_ERROR("000042",
				"用户voip信息查找失败"), FIND_FRIENDS_COMFIRMED_SUCCESS("000043",
				"获取确认的好友成功"), FIND_FRIENDS_COMFIRMED_ERROR("000044",
				"获取确认的好友错误"), UPDATE_FRIEND_REMARK_ERROR("000045", "修改好友备注失败"), ADD_FRIEND_ERROR_PHONE_NOT_EXIST(
				"000046", "请求添加的手机号用户不存在"), ADD_FRIEND_REQUEST_SUCCESS(
				"000047", "添加好友请求发送成功"), FIND_USER_INFO_BY_PHONE_ERROR(
				"000048", "该手机注册的用户不存在"), FIND_USER_INFO_BY_PHONE_SUCCESS(
				"000049", "获取手机用户信息成功"), FIND_UNCONFIRMED_FRIEND_ERROR(
				"000050", "暂无好友申请"), FIND_UNCONFIRMED_FRIEND_SUCCESS("000051",
				"好友申请列表"), ADD_FEEDBACK_ERROR("000052", "问题反馈失败"), FIND_BLOOD_SUGAR_LAST_INFO_SUCCESS(
				"000053", "获取最近血糖成功"), FIND_BLOOD_SUGAR_LAST_INFO_ERROR(
				"000054", "获取最近血糖失败"), ADD_MEDICINE_SUCCESS("000055", "添加医药成功"), ADD_MEDICINE_ERROR(
				"000056", "添加医药失败"), DELETE_MEDICINE_SUCCESS("000057", "删除医药成功"), DELETE_MEDICINE_ERROR(
				"000058", "删除医药失败"), UPDATE_MEDICINE_SUCCESS("000059", "更新医药成功"), UPDATE_MEDICINE_ERROR(
				"000060", "更新医药失败"), FIND_PATIENT_FIRST_BLOOD_PRESSURE_SUCCESS(
				"000061", "获取病人第一条血压测试数据成功"), FIND_PATIENT_FIRST_BLOOD_PRESSURE_ERROR(
				"000062", "获取病人第一条血压测试数据失败"), FIND_PATIENT_BLOOD_PRESSURE_COUNT_SUCCESS(
				"000063", "获取病人血压记录数成功"), FIND_PATIENT_BLOOD_PRESSURE_COUNT_ERROR(
				"000064", "获取病人血压记录数失败"), FIND_PATIENT_FIRST_BLOOD_SUGAR_SUCCESS(
				"000065", "获取病人第一条血糖测试数据成功"), FIND_PATIENT_FIRST_BLOOD_SUGAR_ERROR(
				"000066", "获取病人第一条血糖测试数据失败"), FIND_PATIENT_BLOOD_SUGAR_COUNT_SUCCESS(
				"000067", "获取病人血糖记录数成功"), FIND_PATIENT_BLOOD_SUGAR_COUNT_ERROR(
				"000068", "获取病人血糖记录数失败"), REGISTER_VISIT_SUCCESS("000069",
				"添加回访成功"), REGISTER_VISIT_ERROR("000070", "添加回访失败"), UPDATE_VISIT_SUCCESS(
				"000071", "编辑回访成功"), UPDATE_VISIT_ERROR("000072", "编辑回访失败"), DELETE_VISIT_SUCCESS(
				"000073", "删除回访成功"), DELETE_VISIT_ERROR("000074", "删除回访失败"), SEARCH_VISIT_SUCCESS(
				"000075", "查找回访记录成功"), SEARCH_VISIT_ERROR("000076", "查找回访记录失败"), ADD_GROUP_SUCCESS(
				"000077", "添加群组成功"), ADD_GROUP_ERROR("000078", "添加群组失败"), UPDATE_GROUP_SUCCESS(
				"000079", "编辑群组成功"), UPDATE_GROUP_ERROR("000080", "编辑群组失败"), DELETE_GROUP_SUCCESS(
				"000081", "删除群组成功"), DELETE_GROUP_ERROR("000082", "删除群组失败"), ADD_IDENTITY_SUCCESS(
				"000083", "注册身份证病人成功"), ADD_GROUP_PATIENT_SUCCESS("000084",
				"添加群组病人成功"), ADD_GROUP_PATIENT_ERROR("000085", "添加群组病人失败"), UPDATE_GROUP_PATIENT_SUCCESS(
				"000086", "编辑群组病人成功"), UPDATE_GROUP_PATIENT_ERROR("000087",
				"编辑群组病人失败"), DELETE_GROUP_PATIENT_SUCCESS("000088", "删除群组病人成功"), DELETE_GROUP_PATIENT_ERROR(
				"000089", "删除群组病人失败"), SEARCH_GROUP_PATIENT_SUCCESS("000090",
				"查找群组病人成功"), SEARCH_GROUP_PATIENT_ERROR("000091", "查找群组病人失败"), ADD_GROUP_PATIENT_FAIL(
				"000092", "病人已在该群组"), REGISTER_DOC_SUCCESS("000093", "注册医生成功"), PROVINCE_DELETE_ERROR(
				"000094", "删除省份失败"), CITY_REGISTER_SUCCESS("000095", "注册城市成功"), HOSPITAL_REGISTER_SUCCESS(
				"000096", "注册城市成功"), UPDATE_DOC_SUCCESS("000097", "更新医生信息成功"), UPDATE_PHONE_PASSWORD_SUCCESS(
				"000098", "修改手机密码成功"), UPDATE_PHONE_VALID_NOT_RIGHT("000099",
				"手机验证码不正确"), UPDATE_PHONE_NOT_EXIST("000100", "需要修改的手机号不存在"), FIND_DOCTOR_INFO_SUCCESS(
				"000101", "获取医生信息成功"), FIND_DOCTOR_INFO_ERROR("000102",
				"获取医生信息失败"), ADD_DOCTOR_SUCCESS("000103", "添加医生好友成功"), ADD_DOCTOR_ERROR(
				"000104", "添加医生好友失败"), FIND_DOC_INFO_BY_PHONE_ERROR("000105",
				"该手机注册的医生不存在"), FIND_DOC_INFO_BY_PHONE_SUCCESS("000106",
				"获取手机医生信息成功"), FIND_DOC_INFO_SUCCESS("000107", "获取医生详细信息成功"), FIND_DOC_INFO_ERROR(
				"000108", "获取医生详细信息失败"), SEARCH_GROUP_SUCCESS("000109",
				"获取医生所有组成功"), SEARCH_GROUP_ERROR("000110", "获取医生所有组失败"), UPLOAD_APP_SUCCESS(
				"000111", "上传app软件成功"), UPLOAD_APP_ERROR("000112", "上传app软件失败"), UPLOAD_APP_FILE_NONE(
				"000113", "上传app软件包为空"), SEARCH_APP_INFO_SUCCESS("000114",
				"查找app版本成功"), SEARCH_APP_INFO_ERROR("000115", "查找app版本失败"), UPDATE_DOCTOR_SUCCESS(
				"000116", "更新医生个人信息成功"), UPDATE_DOCTOR_ERROR("000117",
				"更新医生个人信息失败"), SEARCH_VISIT_HISTORY_SUCCESS("000118",
				"查找回访历史成功"), SEARCH_VISIT_HISTORY_ERROR("000119", "查找回访历史失败"), ADD_VISIT_REPORT_SUCCESS(
				"000120", "添加报告成功"), ADD_VISIT_REPORT_ERROR("000121", "添加报告失败"), FIND_ALL_PROVINCE_SUCCESS(
				"000122", "查找所有省份成功"), FIND_ALL_PROVINCE_ERROR("000123",
				"查找所有省份失败"), FIND_ALL_CITY_BY_PROVINCE_SUCCESS("000124",
				"查找省下市成功"), FIND_ALL_CITY_BY_PROVINCE_ERROR("000125", "查找省下市失败"), FIND_ALL_HOSPITAL_BY_CITY_SUCCESS(
				"000126", "查找市下医院成功"), FIND_ALL_HOSPITAL_BY_CITY_ERROR(
				"000127", "查找市下医院失败"), FIND_ALL_DOC_BY_HOSPITAL_SUCCESS(
				"000128", "查找医院下医生成功"), FIND_ALL_DOC_BY_HOSPITAL_ERROR(
				"000129", "查找医院下医生失败"), SETTING_DINNER_SUCCESS("000130",
				"设置用餐时间成功"), SETTING_DINNER_ERROR("000131", "设置用餐时间失败"), ADD_BLOOD_PRESSURE_SUCCESS(
				"000132", "添加血压值成功"), ADD_BLOOD_PRESSURE_ERROR("000133",
				"添加血压值失败"), GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS(
				"000134", "根据病人id和时间段查找血压值成功"), GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR(
				"000135", "根据病人id和时间段查找血压值失败"), GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_SUCCESS(
				"000136", "根据病人id和结束时间查找限制的血压值成功"), GET_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_ERROR(
				"000137", "根据病人id和结束时间查找限制的血压值失败"), ADD_BLOOD_SUGAR_SUCCESS(
				"000138", "添加血糖值成功"), ADD_BLOOD_SUGAR_ERROR("000139", "添加血糖值失败"), GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS(
				"000139", "根据病人id和时间段查找血糖值成功"), GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR(
				"000140", "根据病人id和时间段查找血糖值失败"), GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_SUCCESS(
				"000141", "根据病人id和结束时间查找限制的血糖值成功"), GET_BLOOD_SUGAR_BY_PATIENT_ID_WITH_ENDTIME_AND_LIMIT_ERROR(
				"000142", "根据病人id和结束时间查找限制的血糖值失败"), SYN_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS(
				"000143", "同步获取摸个时间段的血糖数据成功"), SYN_BLOOD_SUGAR_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR(
				"000144", "同步获取摸个时间段的血糖数据失败"), SYN_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_SUCCESS(
				"000145", "同步获取摸个时间段的血压数据成功"), SYN_BLOOD_PRESSURE_BY_PATIENT_ID_WITH_TIME_RANGE_ERROR(
				"000146", "同步获取摸个时间段的血压数据失败"), GET_EXERCISE_CODE_FAIL("000147",
				"获取运动信息失败"), GET_EXERCISE_CODE_SUCESS("000148", "获取运动信息成功"), GET_EXERCISE_TYPE_CODE_FAIL(
				"000149", "获取运动类型失败"), GET_EXERCISE_TYPE_CODE_SUCESS("000150",
				"获取运动类型成功"), SAVE_EXERCISE_CODE_FAIL("000151", "保存运动信息失败"), SAVE_EXERCISE_CODE_SUCESS(
				"000152", "保存运动信息成功"), GET_EXERCISE_P_CODE_FAIL("000153",
				"获取运动处方失败"), GET_EXERCISE_P_CODE_SUCESS("000154", "获取运动处方成功"), GET_EXERCISE_SUMMERY_CODE_FAIL(
				"000155", "获取运动总信息失败"), GET_EXERCISE_SUMMERY_CODE_SUCESS(
				"000156", "获取运动总信息成功"), GET_BLOOD_SUGAR_MONTH_COUNT_BY_PATIENT_ID_SUCCESS(
				"000157", "获取血糖的月和月统计数成功"), GET_BLOOD_SUGAR_MONTH_COUNT_BY_PATIENT_ID_ERROR(
				"000158", "获取血糖的月和月统计数失败"), GET_BLOOD_SUGAR_DATE_COUNT_BY_PATIENT_ID_SUCCESS(
				"000159", "获取血糖的日期和月统计数成功"), GET_BLOOD_SUGAR_DATE_COUNT_BY_PATIENT_ID_ERROR(
				"000160", "获取血糖的日期和月统计数失败"), GET_BLOOD_PRESSURE_MONTH_COUNT_BY_PATIENT_ID_SUCCESS(
				"000161", "获取血压的月和月统计数成功"), GET_BLOOD_PRESSURE_MONTH_COUNT_BY_PATIENT_ID_ERROR(
				"000162", "获取血压的月和月统计数失败"), GET_BLOOD_PRESSURE_DATE_COUNT_BY_PATIENT_ID_SUCCESS(
				"000163", "获取血压的日期和月统计数成功"), GET_BLOOD_PRESSURE_DATE_COUNT_BY_PATIENT_ID_ERROR(
				"000164", "获取血压的日期和月统计数失败"), INQUIRY_QUESTION_SUCCESS("000165",
				"提问成功"), INQUIRY_QUESTION_ERROR("000166", "提问失败"), INQUIRY_HISTORY_QUESTION_SUCCESS(
				"000167", "查找咨询历史成功"), INQUIRY_HISTORY_QUESTION_ERROR("000168",
				"查找咨询历史失败"), INQUIRY_DOC_HISTORY_QUESTION_SUCCESS("000169",
				"查找医生咨询历史成功"), INQUIRY_DOC_HISTORY_QUESTION_ERROR("000170",
				"查找医生咨询历史失败"), INQUIRY_BOOK_SUCCESS("000171", "预定医生信息成功"), INQUIRY_BOOK_ERROR(
				"000172", "预定医生信息失败");

		// 成员变量
		private String code;
		private String msg;

		// 构造方法
		private ResponseCode(String code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		// 覆盖方法
		@Override
		public String toString() {
			return this.code + "_" + this.msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	public static void main(String[] args) {
		System.out.println(ResponseCode.EXIST.toString());
	}
}
