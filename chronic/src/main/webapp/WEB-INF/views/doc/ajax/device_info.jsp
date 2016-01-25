<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="row">
	<div id="breadcrumb" class="col-md-12">
		<ol class="breadcrumb">
			<li><a href="index.do">医生管理</a></li>
			<li><a href="index.do#users_info.do">设备列表</a></li>
		</ol>
	</div>
</div>
<div class="row">
	<div class="col-xs-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-usd"></i> <span>设备详细信息</span>|<a href="#"
						onclick="javacript:LoadAjaxContent('device_add.do')">添加</a>
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
							<th><label><input type="text" name="search_rate"
									value="id" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="出厂编号" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="设备名称" class="search_init" /></label></th>
							<th><label><input type="text" name="search_rate"
									value="更多操作" class="search_init" /></label></th>
						</tr>
					</thead>
					<tbody>
						<!-- Start: list_row -->
						<c:forEach items="${devices}" var="device">
							<tr>
								<td>${device.id}</td>
								<td>${device.deviceId}</td>
								<td>${device.name}</td>
								<td><a href="#"
									onclick="javacript:LoadAjaxContent('device_edit.do?id=${device.id}')">编辑</a>|<a
									href="device_delete.do?id=${device.id}">删除</a></td>
							</tr>
						</c:forEach>
						<!-- End: list_row -->
					</tbody>
					<tfoot>
						<tr>
							<th>id</th>
							<th>出厂编号</th>
							<th>设备名称</th>
							<th>操作</th>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// Run Datables plugin and create 3 variants of settings
	function AllTables() {
		TestTable2();
		TestTable4();
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
	function TestTable4() {
		var asInitVals = [];
		var oTable = $('#datatable-4')
				.dataTable(
						{
							"aaSorting" : [ [ 0, "asc" ] ],
							"sDom" : "<'box-content'<'col-sm-6'f><'col-sm-6 text-right'l><'clearfix'>>rt<'box-content'<'col-sm-6'i><'col-sm-6 text-right'p><'clearfix'>>",
							"sPaginationType" : "bootstrap",
							"oLanguage" : {
								"sSearch" : "",
								"sLengthMenu" : '_MENU_'
							},
							bAutoWidth : false
						});
		var header_inputs = $("#datatable-4 thead input");
		header_inputs.on('keyup', function() {
			/* Filter on the column (the index) of this element */
			oTable.fnFilter(this.value, header_inputs.index(this));
		}).on('focus', function() {
			if (this.className == "search_init") {
				this.className = "";
				this.value = "";
			}
		}).on('blur', function(i) {
			if (this.value == "") {
				this.className = "search_init";
				this.value = asInitVals[header_inputs.index(this)];
			}
		});
		header_inputs.each(function(i) {
			asInitVals[i] = this.value;
		});
	}
	$(document).ready(function() {
		// Load Datatables and run plugin on tables 
		LoadDataTablesScripts(AllTables);
		// Add Drag-n-Drop feature
		WinMove();
	});
</script>
