<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Custom DataGrid Pager - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${path}/resources/jquery-easyui-1.3.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${path}/resources/jquery-easyui-1.3.2/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${path}/resources/jquery-easyui-1.3.2/demo/demo.css">
	<script type="text/javascript" src="${path}/resources/jquery-easyui-1.3.2/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${path}/resources/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	
	<script type="text/javascript">
	$(document).ready(function() {
		loadList(); 
	});
	function loadList(){
		var name = $.trim($('#nameQuery').val());
		$('#dataTable').datagrid({ 
			fit:true,
			pagination : true,
			fitColumns:true,
			striped : true,
			selected : true,
			nowrap : true,
			collapsible : true,
			border : false,
			rownumbers : true,
			queryParams: {
				name: name
			},
			dataType : 'json',
			idField : 'id',
			showHeader : true,
			showFooter : true,
		    url:'/order/userInfo/userList.do', 
		    columns : [[ {
				 field: 'id', checkbox : true
			},{
				field : 'department',
				title : '<strong>所属部门</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'name',
				title : '<strong>名字</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'age',
				title : '<strong>年龄</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'address',
				title : '<strong>地区</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'operate',
				title : '<strong>操作</strong>',
				halign : 'center',
				align : 'center',
				width : 160,
				formatter : function(value, rowData) {
					if (rowData.cannotModified)
						return '';
					
					var id = rowData.id;
					var html = '<div>';
					if (rowData.status != 'N') {
						html += '<sh:permission funcId="">';
						html += '<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="view(\'' + id + '\')">查看</a>';
						html += '</sh:permission>';
					}
					html += '<sh:permission funcId="">';
					html += '<a href="#" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="doDelete(\'' + id + '\')">删除</a>';
					html += '</sh:permission>';
					html += '</div>';
					return html;
				}
			} ]],
			rowStyler : function(rowIndex, rowData) {
				if (rowData.status != 'Y')
					return 'color: #000000;';
			},
			onLoadSuccess : function(data) {
				$.parser.parse(); // 绘制操作列的按钮的样式
			}
		});
	}
	function view(id) {
		var url = '/order/userInfo/view.do';
		var param = '?id=' + id;
		$('#editDiv').dialog({
			title : '修改',
			href : url + param,
			width : 300,
			height : 200,
			modal : true,
			collapsible : true,
		    cache : false
		});
	}
	function doDelete(id) {
		var message = '确定要删除吗？';
		$.messager.confirm('确认', message, function(result) {
			if (!result)
				return;
			var mydata = '{"id":"'+ id + '"}'; 
			$.ajax({
				type : 'post',
				url : '/order/userInfo/doDelete.do',
				data : mydata,
				dataType : 'json',
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					var result = data.result;
					if (result == 'success') {
						$.messager.alert('提示', '操作成功！');
						loadList();
						$('#dataTable').datagrid('clearChecked');
					} else {
						$.messager.alert('提示', '操作失败！<br />');
					}
				}
			});
		});
	}
	function reset(){
		document.getElementById("table").reset(); 
	}
	</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px;">
	<form id="table">
	<table  class="table2" style="border-bottom: 1px solid #ccc">
		<tr height="39px">
			<td style="padding-left: 10px;">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="nameQuery" type="text" placeholder="输入人员名查询" style="margin-right: 20px" maxlength="20"/>
				<a href="#" class="easyui-linkbutton" icon="icon-search" style="margin-right: 10px" onclick="loadList()">查询</a>
				<a id="reset" href="#" class="easyui-linkbutton"  style="margin-right: 10px" onclick="reset()">重置</a>
				
 			</td>
		</tr>
	</table>
	</form>
</div>
<div data-options="region:'center',title:'人员管理',split:true">
	<table id="dataTable"></table>
	<div id="editDiv" style="padding: 5px;"></div>
</div>
</body>
</html>