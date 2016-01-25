<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生管理后台</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('device_edit.do')">编辑设备</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i> <span>编辑设备信息</span>
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
				<h4 class="page-header">填写设备信息</h4>
				<form class="form-horizontal" role="form" method="POST"
					id="defaultForm" action="device_edit_json.do">
					<div class="form-group">
						<label class="col-sm-2 control-label">设备出厂编号</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="deviceId"
								value="${device.deviceId}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">设备别名</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" name="name"
								value="${device.name}" />
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
							<select class="populate placeholder" name="hospitalId"
								id="hospital">
								<option value="${device.hospital.id}">${device.hospital.name}</option>
							</select>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-2">
							<button type="submit" class="btn btn-primary btn-label-left">
								<span><i class="fa fa-clock-o"></i></span> 提交
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
		// Add tooltip to form-controls
		$('.form-control').tooltip();
		LoadBootstrapValidatorScript(DemoFormValidator);
		LoadSelect2Script(DemoSelect2);
		//加載省
		loadProvince();
		// Add drag-n-drop feature to boxes
		bang();
		WinMove();
	});
</script>
