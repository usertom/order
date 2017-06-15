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
		<td>图片：</td>
		<td><img src="${path}/upload/${food.url}"></td>
	</tr>
	<tr>
		<td>菜式：</td>
		<td><input id="title" type="text"  value="${food.name}"  disabled="disabled"/></td>
	</tr>
	<tr>
		<td>内容：</td>
		<td><input id="content" type="text"  value="${food.content}" disabled="disabled"/></td>
	</tr>
	<tr>
		<td>价钱：</td>
		<td><input id="cost" type="text" value="${food.cost }" disabled="disabled"/></td>
	</tr>
	<tr>
		<td>星期：</td>
		<td><input id="day" type="text" value="${food.day }" disabled="disabled"/></td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="50">
			<a id="saveButton" class="easyui-linkbutton" icon="icon-ok" onclick="cancel()">确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="cancel()">取消</a>
		</td>
	</tr>
</table>