<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#name').validatebox({
		required : true,
		missingMessage : '请输入名字'
	});
	$('#address').validatebox({
		required : true,
		missingMessage : '请输入地区'
	});
	$('#age').numberbox({
		min:0,
		required : true,
		missingMessage : '请输入年龄'
	});
	$('#salary').numberbox({
		min:0,
		required : true,
		missingMessage : '请输入工资'
	});
	$('#name').focus();
});

function isValid() {
	if (!$('#name').validatebox('isValid'))
		return false;
	if (!$('#address').validatebox('isValid'))
		return false;
	if (!$('#age').numberbox('isValid'))
		return false;
	if (!$('#salary').numberbox('isValid'))
		return false;
	return true;
}

	function save() {
		if (!isValid())
			return;
		var name = $.trim($('#name').val());
		var address = $.trim($('#address').val());
		var age = $.trim($('#age').val());
		var salary = $.trim($('#salary').val());
		var id = "${userInfo.id}";
		var mydata = '{"id":"'+ id + '","departmentId":"'+ 1 + '","salary":"'+ salary + '","age":"'+ age + '","address":"'+ address + '","name":"'+ name + '"}'; 
		$.ajax({
			type : 'post',
			url : '/order/userInfo/doSave.do',
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
		<td>名字：</td>
		<td><input id="name" type="text"  value="${userInfo.name}" maxLength="16"/></td>
	</tr>
	<tr>
		<td>年龄：</td>
		<td><input id="age" type="text"  value="${userInfo.age}" maxLength="16"/></td>
	</tr>
	<tr>
		<td>地区：</td>
		<td><input id="address" type="text"  value="${userInfo.address}" maxLength="16"/></td>
	</tr>
	<tr>
		<td>工资：</td>
		<td><input id="salary" type="text"  value="${userInfo.salary}" maxLength="16"/></td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="50">
			<a id="saveButton" class="easyui-linkbutton" icon="icon-ok" onclick="save()">确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="cancel()">取消</a>
		</td>
	</tr>
</table>