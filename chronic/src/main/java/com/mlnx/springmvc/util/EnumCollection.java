package com.mlnx.springmvc.util;

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
				"000054", "获取最近血糖失败"), ADD_MEDCINE_SUCCESS("000055", "添加医药成功"), ADD_MEDCINE_ERROR(
				"000056", "添加医药失败"), DELETE_MEDCINE_SUCCESS("000057", "删除医药成功"), DELETE_MEDCINE_ERROR(
				"000058", "删除医药失败"), UPDATE_MEDCINE_SUCCESS("000059", "更新医药成功"), UPDATE_MEDCINE_ERROR(
				"000060", "更新医药失败"), FIND_PATIENT_FIRST_BLOOD_PRESSURE_SUCCESS(
				"000061", "获取病人第一条血压测试数据成功"), FIND_PATIENT_FIRST_BLOOD_PRESSURE_ERROR(
				"000062", "获取病人第一条血压测试数据失败"), FIND_PATIENT_BLOOD_PRESSURE_COUNT_SUCCESS(
				"000063", "获取病人血压记录数成功"), FIND_PATIENT_BLOOD_PRESSURE_COUNT_ERROR(
				"000064", "获取病人血压记录数失败"), FIND_PATIENT_FIRST_BLOOD_SUGAR_SUCCESS(
				"000065", "获取病人第一条血糖测试数据成功"), FIND_PATIENT_FIRST_BLOOD_SUGAR_ERROR(
				"000066", "获取病人第一条血糖测试数据失败"), FIND_PATIENT_BLOOD_SUGAR_COUNT_SUCCESS(
				"000067", "获取病人血糖记录数成功"), FIND_PATIENT_BLOOD_SUGAR_COUNT_ERROR(
				"000068", "获取病人血糖记录数失败");

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
