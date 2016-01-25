<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('data_info.do')">服务管理</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('data_info.do')">数据统计</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>医生统计</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-2">
					<thead>
						<tr>
							<th><label>医生</label></th>
							<th><label>门诊设备使用次数/门诊次数=设备使用率</label></th>
							<th><label>回访设备使用次数/回访次数=设备使用率</label></th>
							<th><label>设备使用次数/服务次数=设备使用率</label></th>
							<th><label>操作</label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${serviceData}" var="service">
							<tr>
								<td><img class="img-rounded" src="${service.pic}"
									alt="${service.pic}"
									onerror="this.src='../common/img/admin/avatar.jpg'" />${service.name}</td>
								<td><fmt:formatNumber
										value="${service.clinic_device_count}" pattern="#"
										type="number" />/<fmt:formatNumber
										value="${service.clinic_count}" pattern="#" type="number" />=<c:if
										test="${service.clinic_rate=='NaN'}">0%</c:if> <c:if
										test="${service.clinic_rate!='NaN'}">
										<fmt:formatNumber value="${service.clinic_rate}"
											type="percent" />
									</c:if></td>
								<td><fmt:formatNumber
										value="${service.callback_device_count}" pattern="#"
										type="number" />/<fmt:formatNumber
										value="${service.callback_count}" pattern="#" type="number" />=<c:if
										test="${service.callback_rate=='NaN'}">0%</c:if> <c:if
										test="${service.callback_rate!='NaN'}">
										<fmt:formatNumber value="${service.callback_rate}"
											type="percent" />
									</c:if></td>
								<td><fmt:formatNumber value="${service.use_device_count}"
										pattern="#" type="number" />/<fmt:formatNumber
										value="${service.sum}" pattern="#" type="number" />=<c:if
										test="${service.rate=='NaN'}">0%</c:if> <c:if
										test="${service.rate!='NaN'}">
										<fmt:formatNumber value="${service.rate}" type="percent" />
									</c:if></td>
								<td><a href="#" onclick="LoadPatients(${service.user_id})">查看病人</a>
								</td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th><label>医生</label></th>
							<th><label>门诊设备使用次数/门诊次数=设备使用率</label></th>
							<th><label>回访设备使用次数/回访次数=设备使用率</label></th>
							<th><label>设备使用次数/服务次数=设备使用率</label></th>
							<th><label>操作</label></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	
	<!-- 医生病人统计  -->
	<!--Start Content-->
	<div id="content-patients" class="col-xs-12">
		<div class="preloader-ajax">
			<img src="${ctx}/common/img/admin/devoops_getdata.gif"
				class="devoops-getdata" alt="preloader" />
		</div>
		<div id="ajax-content-patients"></div>
	</div>
	<!--End Content-->
	
	<!--  服务统计  -->
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>服务统计</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-6">
					<thead>
						<tr>
							<th><label>医生</label></th>
							<th><label>病人</label></th>
							<th><label>门诊/回访</label></th>
							<th><label>时间</label></th>
							<th><label>使用设备</label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${services}" var="service">
							<tr>
								<td><img class="img-rounded" src="${service.doc.pic}"
									alt="${service.doc.pic}"
									onerror="this.src='../common/img/admin/avatar.jpg'" />${service.doc.name}</td>
								<td><img class="img-rounded" src="${service.patient.pic}"
									alt="${service.patient.pic}"
									onerror="this.src='../common/img/admin/avatar.jpg'" />${service.patient.name}</td>
								<td><c:if test="${service.type==1}">门诊</c:if>
									<c:if test="${service.type==2}">回访</c:if></td>
								<td><fmt:formatDate value="${service.start}"
										pattern="yyyy-MM-dd HH:mm:ss" />~<fmt:formatDate
										value="${service.end}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><c:forEach items="${service.device}" var="device"><span>${device.name}</span></c:forEach></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th><label>医生</label></th>
							<th><label>病人</label></th>
							<th><label>门诊/回访</label></th>
							<th><label>时间</label></th>
							<th><label>使用设备</label></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	
	<!-- 地区统计  -->
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>地区统计</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-4">
					<thead>
						<tr>
							<th><label>地区</label></th>
							<th><label>门诊设备使用次数/门诊次数=设备使用率</label></th>
							<th><label>回访设备使用次数/回访次数=设备使用率</label></th>
							<th><label>设备使用次数/服务次数=设备使用率</label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${serviceAddressData}" var="service">
							<tr>
								<td>${service.name}</td>
								<td><fmt:formatNumber
										value="${service.clinic_device_count}" pattern="#"
										type="number" />/<fmt:formatNumber
										value="${service.clinic_count}" pattern="#" type="number" />=<c:if
										test="${service.clinic_rate=='NaN'}">0%</c:if> <c:if
										test="${service.clinic_rate!='NaN'}">
										<fmt:formatNumber value="${service.clinic_rate}"
											type="percent" />
									</c:if></td>
								<td><fmt:formatNumber
										value="${service.callback_device_count}" pattern="#"
										type="number" />/<fmt:formatNumber
										value="${service.callback_count}" pattern="#" type="number" />=<c:if
										test="${service.callback_rate=='NaN'}">0%</c:if> <c:if
										test="${service.callback_rate!='NaN'}">
										<fmt:formatNumber value="${service.callback_rate}"
											type="percent" />
									</c:if></td>
								<td><fmt:formatNumber value="${service.use_device_count}"
										pattern="#" type="number" />/<fmt:formatNumber
										value="${service.sum}" pattern="#" type="number" />=<c:if
										test="${service.rate=='NaN'}">0%</c:if> <c:if
										test="${service.rate!='NaN'}">
										<fmt:formatNumber value="${service.rate}" type="percent" />
									</c:if></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th><label>地区</label></th>
							<th><label>门诊设备使用次数/门诊次数=设备使用率</label></th>
							<th><label>回访设备使用次数/回访次数=设备使用率</label></th>
							<th><label>设备使用次数/服务次数=设备使用率</label></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	
	<!-- 设备统计  -->
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>设备统计</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="expand-link"> <i class="fa fa-expand"></i>
					</a> <a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content no-padding">
				<table
					class="table table-bordered table-striped table-hover table-heading table-datatable"
					id="datatable-5">
					<thead>
						<tr>
							<th><label>设备出厂编号</label></th>
							<th><label>设备名称</label></th>
							<th><label>使用次数</label></th>
							<th><label>使用率</label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${serviceDeviceData}" var="service">
							<tr>
								<td>${service.device_id}</td>
								<td>${service.device_name}</td>
								<td><fmt:formatNumber value="${service.device_count}" pattern="#" type="number"/></td>
								<td><fmt:formatNumber value="${service.device_count/services.size()}" type="percent"/></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th><label>设备出厂编号</label></th>
							<th><label>设备名称</label></th>
							<th><label>使用次数</label></th>
							<th><label>使用率</label></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
	
</div>

<script type="text/javascript">
	function AllTables() {
		TestTable2();
		TestTable4();
		TestTable5();
		TestTable6();
		LoadSelect2Script(MakeSelect2);
	}
	function MakeSelect2() {
		$('select').select2();
		$('.dataTables_filter').each(
				function() {
					$(this).find('label input[type=text]').attr('placeholder',
							'Search');
				});
	}
	function LoadPatients(id){
		$('.preloader-ajax').show();
		$.ajax({
			url:  "${ctx}/doc/getPatients/"+id,
			type: 'GET',
			success: function(data) {
				$('#ajax-content-patients').html(data);
				$('.preloader-ajax').hide();
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert(errorThrown);
			},
			dataType: "html",
			async: false
		});
	}
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
