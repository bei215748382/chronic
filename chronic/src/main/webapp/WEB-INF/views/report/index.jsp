<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>报告网页版样板</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${ctx}/common/plugins/bootstrap/bootstrap.css"
	rel="stylesheet">
<link href="${ctx}/common/plugins/jquery-ui/jquery-ui.min.css"
	rel="stylesheet">
<link href="${ctx}/common/css/report/css.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
				<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
				<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
		<![endif]-->
</head>

<body onload="pageLoad();">
	<div class="container">
		<!-- 报告简介  -->
		<div class="row top15">
			<div class="col-xs-8">
				<div class="row">
					<div class="col-xs-5">
						<img class="img-rounded" src="${userExt.pic}" alt="${userExt.pic}"
							onerror="this.src='../common/img/admin/avatar.jpg'">
					</div>
					<div class="col-xs-7">
						<div>
							<label>年龄：${userExt.age}</label>
						</div>
						<div>
							<label>性别：${userExt.sex}</label>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-4">
				<div class="row">

					<label>分析日期：</label>
				</div>
				<div class="row">
					<fmt:formatDate value="${report.time}" pattern="yyyy-MM-dd" />
				</div>

			</div>
		</div>

		<div class="row">
			<hr />
		</div>

		<!-- 报告的综合得分和各项指标说明 -->
		<div class="row">
			<label>您的健康综合得分：</label>
			<canvas id="can">4</canvas>
			<p>${report.body}</p>
		</div>

		<div class="row">
			<hr />
		</div>

		<!-- 心电、血压、血糖三份报告  -->
		<div class="row">
			<label>健康报告</label>
		</div>

		<div class="row">
			<hr />
		</div>

		<!-- 报告给出的健康建议 -->
		<div class="row">
			<label>健康建议</label>
			<p>${report.suggest}</p>
		</div>
	</div>
	<!--End Container-->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!--<script src="http://code.jquery.com/jquery.js"></script>-->
	<script src="${ctx}/common/plugins/jquery/jquery-2.1.0.min.js"></script>
	<script src="${ctx}/common/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${ctx}/common/plugins/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript">
		function pageLoad() {
			var windowWidth = $(".container").width();
			var can = document.getElementById("can");
			can.width = windowWidth;
			can.height = 140;
			var cans = can.getContext('2d');
			cans.beginPath();
			cans.arc(windowWidth / 2, 80, 50, 0, Math.PI * 2, true);
			cans.closePath();
			cans.lineWidth = 5;
			cans.textAlign = 'center';//水平居中
			cans.textBaseline = 'middle';
			var s = '${report.time}';
			cans.font = 'bold 50px consolas';
			cans.fillText(s, windowWidth / 2, 80);
			cans.strokeStyle = 'green';//本来这里最初使用的是red，截图一看，傻眼了，怕上街被爱国者打啊，其实你懂的~~
			cans.stroke();
		}
	</script>
</body>
</html>
