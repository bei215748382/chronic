<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>病人统计</span>
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
					class="table table-bordered table-striped table-hover table-heading table-datatable" id="datatable-5">
					<thead>
						<tr>
							<th><label>头像姓名</label></th>
							<th><label>性别</label></th>
							<th><label>生日</label></th>
							<th><label>新增时间</label></th>
						</tr>
					</thead>
					<tbody id="patients">
						<!-- Start: list_row -->
							<c:forEach items="${patients}" var="patient">
							<tr>
								<td><img class="img-rounded" src="${patient.pic}"
									alt="${patient.pic}"
									onerror="this.src='../common/img/admin/avatar.jpg'" />${patient.name}</td>
								<td>${patient.sex}</td>
								<td><fmt:formatDate value="${patient.birthday}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${patient.user.date}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th><label>头像姓名</label></th>
							<th><label>性别</label></th>
							<th><label>生日</label></th>
							<th><label>新增时间</label></th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function AllTables() {
		TestTable5();
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
