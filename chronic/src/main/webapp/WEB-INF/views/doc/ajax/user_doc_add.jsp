<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生信息管理</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('user_doc_add')">添加医生</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>填写医生详情</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<h4 class="page-header">填写医生信息</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="defaultForm" action="doctor_add_json.do"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-2 control-label">手机号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="phone" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-4">
							<input type="password" class="form-control" name="password" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-4">
							<input type="password" class="form-control"
								name="confirmPassword" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" placeholder="真实姓名"
								data-toggle="tooltip" data-placement="top" title="输入医生真实姓名"
								name="name">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">头像</label>
						<div id="localImag" class="col-sm-4">
							<img id="preview" class="img-rounded" src="" alt="" /> <input
								type="file" name="file"
								onchange="javascript:setImagePreview(this,localImag,preview);"
								class="margin-top-15">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-4">
							<div class="radio-inline">
								<label> <input type="radio" name="sex" value="男" checked>
									男 <i class="fa fa-circle-o"></i>
								</label>
							</div>
							<div class="radio-inline">
								<label> <input type="radio" name="sex" value="女">
									女 <i class="fa fa-circle-o"></i>
								</label>
							</div>
						</div>
					</div>
					<div class="form-group has-feedback">
						<label class="col-sm-2 control-label">出生年月</label>
						<div class="col-sm-2">
							<input type="text" id="input_date" class="form-control"
								placeholder="Date" name="birthday"> <span
								class="fa fa-calendar txt-danger form-control-feedback"></span>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">所属医院</label>
						<div class="col-sm-2">
							<select class="populate placeholder" name="province"
								id="province">
								<option value=''>-- 选择省 --</option>
							</select>
						</div>
						<div class="col-sm-2">
							<select class="populate placeholder" name="city" id="city">
								<option value="">-- 选择市 --</option>
							</select>
						</div>
						<div class="col-sm-3">
							<select class="populate placeholder" name="hospital_id"
								id="hospital">
								<option value="">-- 选择医院 --</option>
							</select>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-sm-2 control-label">科室</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" placeholder="科室"
								name="office">
						</div>
						<label class="col-sm-2 control-label">职称</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" placeholder="职称"
								name="title">
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label" for="form-styles">擅长领域</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="skill" name="skill"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="form-styles">医学背景</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="background"
								name="background"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="form-styles">学术成果</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="achievement"
								name="achievement"></textarea>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2">
							<button type="submit" class="btn btn-primary btn-label-left">
								<span><i class="fa fa-clock-o"></i></span> Submit
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function DemoSelect2() {
		$('#province').select2();
		$('#city').select2();
		$('#hospital').select2();
	}
	function loadProvince() {
		$.ajax({
			mimeType : 'text/html; charset=utf-8',
			url : "../provinces/all.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择省 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#province").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	function bang() {
		$("#province").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_city .select2-chosen").html("-- 选择市 --");
			loadCities(p1);
		});
		$("#city").change(function() {
			var p1 = $(this).children('option:selected').val();//这就是selected的值 
			$("#s2id_hospital .select2-chosen").html("-- 选择医院 --");
			loadHospitals(p1);
		});

	}
	function loadCities(provinceId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../cities/find/province/" + provinceId + "/cities.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择市 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#city").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	function loadHospitals(cityId) {
		$.ajax({
			contentType : 'application/json;',
			url : "../hospitals/find/city/" + cityId + "/hospital.do",
			type : 'GET',
			success : function(data) {
				var htmlString = "<option value=''>-- 选择医院 --</option>";
				console.log("data", data);
				for (var i = 0; i < data.length; i++) {
					htmlString += "<option value='"+data[i].id+"'>"
							+ data[i].name + "</option>";
				}
				$("#hospital").html(htmlString);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(errorThrown);
			},
			dataType : "json",
			async : true
		});
	}
	$(document).ready(function() {
		// Create Wysiwig editor for textare
	
		// Initialize datepicker
		$('#input_date').datepicker({
			setDate : new Date()
		});
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadSelect2Script(DemoSelect2);
		// Load example of form validation
		LoadBootstrapValidatorScript(DoctorAddFormValidator);
		// Add drag-n-drop feature to boxes
		WinMove();
		//加載省
		loadProvince();
		//加載市
		bang();
		//加载医院
	});
	function DoctorAddFormValidator() {
		$('#defaultForm')
				.bootstrapValidator(
						{
							feedbackIcons : {
								valid : 'fa fa-check',
								invalid : 'glyphicon glyphicon-remove',
								validating : 'glyphicon glyphicon-refresh'
							},
							message : 'This value is not valid',
							fields : {
								phone : {
									validators : {
										remote : {
											message : '手机号已注册',
											url : '../doc/find/regist/phone'
										},
										notEmpty : {
											message : '手机号不能为空'
										}
									}
								},
								name : {
									validators : {
										notEmpty : {
											message : '姓名不能为空'
										}
									}
								},
								username : {
									message : 'The username is not valid',
									validators : {
										notEmpty : {
											message : '用户名不能为空'
										},
										stringLength : {
											min : 4,
											max : 30,
											message : '用户名的长度在4个和30个字符之间'
										},
										regexp : {
											regexp : /^[a-zA-Z0-9_\.]+$/,
											message : '用户名只允许包含数字、大小写字母下划线和。,不允许包含其他字符'
										}
									}
								},
								acceptTerms : {
									validators : {
										notEmpty : {
											message : '你必须同意本协议'
										}
									}
								},
								email : {
									validators : {
										notEmpty : {
											message : 'The email address is required and can\'t be empty'
										},
										emailAddress : {
											message : 'The input is not a valid email address'
										}
									}
								},
								website : {
									validators : {
										uri : {
											message : 'The input is not a valid URL'
										}
									}
								},
								password : {
									validators : {
										notEmpty : {
											message : '密码不能为空'
										},
										identical : {
											field : 'confirmPassword',
											message : '2次密码不一致'
										}
									}
								},
								confirmPassword : {
									validators : {
										notEmpty : {
											message : '确认密码不能为空'
										},
										identical : {
											field : 'password',
											message : '2次密码输入不一致，请重新输入'
										}
									}
								},
								date : {
									validators : {
										notEmpty : {
											message : '日期不能为空'
										},
										date : {
											format : 'MM/DD/YYYY',
											message : '格式不正确'
										}
									}
								},
								birthday : {
									validators : {
										notEmpty : {
											message : '日期不能为空'
										},
										date : {
											format : 'MM/DD/YYYY',
											message : '格式不正确'
										}
									}
								}
							}
						});
	}
</script>