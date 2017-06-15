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
	function DrawImage(ImgD,width_s,height_s){
		var image = new Image();
		image.src = ImgD.src;
		if(image.width>0 && image.height>0){
			flag = true;
			if(image.width/image.height>=width_s/height_s){
				if(image.width>width_s){
					ImgD.width = width_s;
					ImgD.height = (image.height*width_s)/image.width;
				}
				else{
					ImgD.width = image.width;
					ImgD.height = image.height;
				}
			}
		}
		else{
			if(image.height>height_s){
				ImgD.height = height_s;
				ImgD.width = (image.width*height_s)/image.height;
			}
			else{
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		}
	}
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
			border : true,
			rownumbers : true,
			queryParams: {
				name: name
			},
			dataType : 'json',
			idField : 'id',
			showHeader : true,
			showFooter : true,
		    url:'/order/food/list.do', 
		    columns : [[ {
				 field: 'id', checkbox : true
			},{
				field : 'url',
				title : '<strong>图片</strong>',halign : 'center',align : 'center',sortable : true,width:200,height:100,
				formatter:function(value,row,index){    
					return '<img width="200" height="100" alt="" src="${path}/upload/'+value+'">';  
				}
			},{
				field : 'name',
				title : '<strong>菜式</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'content',
				title : '<strong>内容</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'cost',
				title : '<strong>价钱</strong>',halign : 'center',align : 'center',sortable : true,width : 80
			},{
				field : 'day',
				title : '<strong>星期</strong>',halign : 'center',align : 'center',sortable : true,width : 80
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
					if (rowData.status != 'N') {
						html += '<sh:permission funcId="">';
						html += '<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="upload(\'' + id + '\')">上传图片</a>';
						html += '</sh:permission>';
					}
					if (rowData.status != 'N') {
						html += '<sh:permission funcId="">';
						html += '<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update(\'' + id + '\')">修改</a>';
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
	function add() {
		var url = '/order/food/add.do';
		$('#editDiv').dialog({
			title : '新增',
			href : url,
			width : 300,
			height : 200,
			modal : true,
			collapsible : true,
		    cache : false
		});
	}
	function update(id) {
		var url = '/order/food/update.do';
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
	function upload(id) {
		var url = '/order/food/uploadIndex.do';
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
	function view(id) {
		var url = '/order/food/view.do';
		var param = '?id=' + id;
		$('#editDiv').dialog({
			title : '修改',
			href : url + param,
			width : 400,
			height : 400,
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
				url : '/order/food/doDelete.do',
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
				<input id="nameQuery" type="text" placeholder="输入菜式查询" style="margin-right: 20px" maxlength="20"/>
				<a id="find" href="#" class="easyui-linkbutton" icon="icon-search" style="margin-right: 10px" onclick="loadList()">查询</a>
				<a id="reset" href="#" class="easyui-linkbutton"  style="margin-right: 10px" onclick="reset()">重置</a>
				<a id="add" href="#" class="easyui-linkbutton"  style="margin-right: 10px" onclick="add()">新增菜式</a>
 			</td>
		</tr>
	</table>
	</form>
</div>
<div data-options="region:'center',title:'菜式管理',split:true">
	<table id="dataTable"></table>
	<div id="editDiv" style="padding: 5px;"></div>
</div>
</body>
</html>