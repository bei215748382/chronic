<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Highcharts 教程 | 菜鸟教程(runoob.com)</title>
</head>
<body>
	<div id="container" style="width: 550px; height: 400px; margin: 0 auto"></div>

	<script src="${ctx}/common/plugins/jquery/jquery-2.1.0.min.js"></script>
	<script src="${ctx}/common/plugins/highcharts/highcharts.js"></script>
	<script language="JavaScript">
		$(document).ready(
				function() {
					var chart = {
						type : 'spline',
						animation : Highcharts.svg, // don't animate in IE < IE 10.
						marginRight : 10,
						events : {
							load : function() {
								// set up the updating of the chart each second
								var series = this.series[0];
								setInterval(function() {
									var x = (new Date()).getTime();// current time
									$.ajax({
										url:  "${ctx}/ecg/y/",
										type: 'GET',
										success: function(data) {
											console.log(data);
											series.addPoint([ x, data ], true, true);
										},
										error: function (jqXHR, textStatus, errorThrown) {
// 											alert("数据中断");
										},
										dataType: "json",
									});
								}, 500);
							}
						}
					};
					var title = {
						text : 'Live random data'
					};
					var xAxis = {
						type : 'datetime',
						tickPixelInterval : 150
					};
					var yAxis = {
						title : {
							text : 'Value'
						},
						plotLines : [ {
							value : 0,
							width : 1,
							color : '#808080'
						} ]
					};
					var tooltip = {
						formatter : function() {
							return '<b>'
									+ this.series.name
									+ '</b><br/>'
									+ Highcharts.dateFormat(
											'%Y-%m-%d %H:%M:%S', this.x)
									+ '<br/>'
									+ Highcharts.numberFormat(this.y, 2);
						}
					};
					var plotOptions = {
						area : {
							pointStart : 1940,
							marker : {
								enabled : false,
								symbol : 'circle',
								radius : 2,
								states : {
									hover : {
										enabled : true
									}
								}
							}
						}
					};
					var legend = {
						enabled : false
					};
					var exporting = {
						enabled : false
					};
					var series = [ {
						name : 'Random data',
						data : (function() {
							// generate an array of random data
							var data = [], time = (new Date()).getTime(), i;
							for (i = -45; i <= 0; i += 1) {
								data.push({
									x : time + i * 1000,
									y : Math.random()
								});
							}
							return data;
						}())
					} ];

					var json = {};
					json.chart = chart;
					json.title = title;
					json.tooltip = tooltip;
					json.xAxis = xAxis;
					json.yAxis = yAxis;
					json.legend = legend;
					json.exporting = exporting;
					json.series = series;
					json.plotOptions = plotOptions;

					Highcharts.setOptions({
						global : {
							useUTC : false
						}
					});
					$('#container').highcharts(json);

				});
	</script>
</body>
</html>