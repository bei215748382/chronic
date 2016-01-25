<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('services_info.do')">服务管理</a></li>
			<li><a href="#"
				onclick="javacript:LoadAjaxContent('services_info.do')">服务列表</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>服务列表</span>
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
</div>
<script type="text/javascript">
	function AllTables() {
		TestTable2();
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
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
