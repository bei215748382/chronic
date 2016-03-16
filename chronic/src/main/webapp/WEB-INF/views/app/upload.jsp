<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="form-horizontal" role="form" method="POST" id="defaultForm"
	action="upload" enctype="multipart/form-data">
	<div>
		<label class="col-sm-2 control-label">包名：</label> <input type="text"
			name="name" />
	</div>
	<div>
		<label class="col-sm-2 control-label">版本：</label> <input type="text"
			name="version" />
	</div>
	<div>
		<label class="col-sm-2 control-label">内容：</label> <input type="text"
			name="content" />
	</div>
	<div>
		<label class="col-sm-2 control-label">稳定性：</label> <input type="text"
			name="stable" />
	</div>

	<div class="margin-top-15">
		<label class="col-sm-2 control-label">文件：</label> <input id="doc"
			type="file" name="file">
	</div>
	<div class="clearfix"></div>
	<div class="col-sm-2">
		<button type="submit" class="btn btn-primary btn-label-left">
			<span><i class="fa fa-clock-o"></i></span> 提交
		</button>
	</div>

	<br /> <br />
	<div>
		<a
			href="http://192.168.1.166:8080/chronic/app/download/1.0/app-debug.apk">Android下载</a>
	</div>
	<br />
	<div>
		<a
			href="http://192.168.1.166:8080/chronic/app/download/2.0/iphone.ipa">Iphone下载</a>
	</div>
</form>
