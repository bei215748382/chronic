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
				"000030", "对方的信息你能查看"), ADD_FEEDBACK_SUCCESS("000031","提交反馈问题成功");
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
