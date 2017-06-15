<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script type="text/javascript">
$(document).ready(function() {
	$('#title').validatebox({
		required : true,
		missingMessage : '请输入标题'
	});
	$('#content').validatebox({
		required : true,
		missingMessage : '请输入内容'
	});
	$('#title').focus();
});

function isValid() {
	if (!$('#title').validatebox('isValid'))
		return false;
	if (!$('#content').validatebox('isValid'))
		return false;
	return true;
}

	function save() {
		if (!isValid())
			return;
		var title = $.trim($('#title').val());
		var content = $.trim($('#content').val());
		var id = "${article.id}";
		var mydata = '{"title":"'+ title + '","id":"'+ id + '","content":"'+ content + '"}'; 
		$.ajax({
			type : 'post',
			url : '/order/article/doSave.do',
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
		<td>文章标题：</td>
		<td><input id="title" value="${article.title }" type="text"   /></td>
	</tr>
	<tr>
		<td>内容：</td>
		<td><input id="content"  value="${article.content }" type="text"  /></td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="50">
			<a id="saveButton" class="easyui-linkbutton" icon="icon-ok" onclick="save()">确定</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="cancel()">取消</a>
		</td>
	</tr>
</table>