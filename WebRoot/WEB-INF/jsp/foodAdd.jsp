<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#name').validatebox({
		required : true,
		missingMessage : '请输入菜式'
	});
	$('#content').validatebox({
		required : true,
		missingMessage : '请输入内容'
	});
	$('#cost').numberbox({
		min:0,
		required : true,
		missingMessage : '请输入价格'
	});
	$('#day').validatebox({
		required : true,
		missingMessage : '请输入星期'
	});
	$('#name').focus();
});

function isValid() {
	if (!$('#name').validatebox('isValid'))
		return false;
	if (!$('#content').validatebox('isValid'))
		return false;
	if (!$('#day').validatebox('isValid'))
		return false;
	if (!$('#cost').numberbox('isValid'))
		return false;
	return true;
}

	function save() {
		if (!isValid())
			return;
		var name = $.trim($('#name').val());
		var content = $.trim($('#content').val());
		var cost = $.trim($('#cost').val());
		var day = $.trim($('#day').val());
		var mydata = '{"name":"'+ name + '","cost":"'+ cost + '","day":"'+ day + '","content":"'+ content + '"}'; 
		$.ajax({
			type : 'post',
			url : '/order/food/doSave.do',
			data :mydata,
			dataType : 'json',
			contentType : 'application/json;charset=UTF-8',
			success : function(data) {
				var result = data.result;
				if (result == 'success') {
					$.messager.alert('提示', '操作成功！');
					$('#editDiv').dialog('close');
					loadList();
					$('#dataTable').datagrid('clearChecked');
				} else {
					$.messager.alert('提示', '操作失败！<br />');
				}
			}
		});
	}
	
	function cancel() {
		$('#editDiv').dialog('close');
	}
</script>
<table class="table2">
	<tr>
		<td>菜式：</td>
		<td><input id="name" type="text"   /></td>
	</tr>
	<tr>
		<td>内容：</td>
		<td><input id="content" type="text"  /></td>
	</tr>
	<tr>
		<td>价钱：</td>
		<td><input id="cost" type="text"  /></td>
	</tr>
	<tr>
		<td>星期：</td>
		<td><input id="day" type="text"  /></td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="50">
			<a id="saveButton" class="easyui-linkbutton" icon="icon-ok" onclick="save()">确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="cancel()">取消</a>
		</td>
	</tr>
</table>