<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script type="text/javascript">
	
	function cancel() {
		$('#editDiv').dialog('close');
	}
</script>
<table class="table2">
	<tr>
		<td>名字：</td>
		<td><input id="name" type="text"  value="${userInfo.name}" maxLength="16" readonly="readonly"/></td>
	</tr>
	<tr>
		<td>年龄：</td>
		<td><input id="age" type="text"  value="${userInfo.age}" maxLength="16" readonly="readonly"/></td>
	</tr>
	<tr>
		<td>地区：</td>
		<td><input id="address" type="text"  value="${userInfo.address}" maxLength="16" readonly="readonly"/></td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="50">
			<a id="saveButton" class="easyui-linkbutton" icon="icon-ok" onclick="cancel()">确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="cancel()">取消</a>
		</td>
	</tr>
</table>