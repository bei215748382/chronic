<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>报告网页版样板</title>
<link href="${ctx}/common/plugins/bootstrap/bootstrap.css" rel="stylesheet"></link>
<link href="${ctx}/common/css/report/css.css" rel="stylesheet"></link>
</head>
<body>
	<div class="header">
		<div class="title pd-top15">综合报告</div>
	</div>
	<div class="container">
		<div class="info_panel">
			<div class="avatar">
				<img id="avatar" src="${ctx}/common/img/report2/tx.png" alt="头像" />
			</div>
			<div class="info">
				<div class="pd-4">
					<span class="margin-left-10">年龄：26</span><span class="pull-right">分析日期：</span>
				</div>
				<div class="pd-4">
					<span class="margin-left-10">性别：男</span><span class="pull-right">${data.time}</span>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<div id="score">
			<img src="${ctx}/common/img/report2/score2.png" alt="得分图片"><span
				class="center-text">65</span></img>
		</div>
		<div id="score_desc">${data.body}</div>
		<div class="dividing">
			<hr />
		</div>
		<div class="caption">健康报告</div>
		<div class="report">
			<div class="border-bottom-gray">
				<div class="box-center">
					<img src="${ctx}/common/img/report2/heart.png" alt="心电图标">
				</div>
				<div class="report-desc">
					<div class="report-title">心电报告</div>
					<div class="report-content">心电图反映良好，继续保持</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="border-bottom-gray">
				<div class="box-center">
					<img src="${ctx}/common/img/report2/pressure.png" alt="血压图标">
				</div>
				<div class="report-desc">
					<div class="report-title">血压报告</div>
					<div class="report-content">血压趋势稳定，继续保持</div>
				</div>
			</div>
			<div class="clearfix"></div>
			<div>
				<div class="box-center">
					<img src="${ctx}/common/img/report2/sugar.png" alt="血糖图标">
				</div>
				<div class="report-desc">
					<div class="report-title">血糖报告</div>
					<div class="report-content">血糖在合理的范围内，比较稳定，继续保持。</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="caption">健康建议</div>
		<div id="suggest">
			<div>${data.suggest}</div>
			<img class="margin-top-20" src="${ctx}/common/img/report2/water.png"
				alt="多喝水"><img class="margin-top--20"
				src="${ctx}/common/img/report2/food.png" alt="注意饮食"><img
				class="margin-top-20" src="${ctx}/common/img/report2/rest.png" alt="注意休息"><img
				class="margin-top--20" src="${ctx}/common/img/report2/exercise.png"
				alt="注意锻炼"><img class="margin-top-20"
				src="${ctx}/common/img/report2/test.png" alt="定期复诊">
		</div>
	</div>
	<script src="${ctx}/common/plugins/jquery/jquery-2.1.0.min.js"></script>
	<script src="${ctx}/common/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${ctx}/common/plugins/bootstrap/bootstrap.min.js"></script>
</body>
</html>