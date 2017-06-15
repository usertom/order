<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>后台登录界面</title>

<link href="${path}/resources/login/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/resources/login/css/signin.css" rel="stylesheet">

</head>

<body>

<div class="signin">
	<div class="signin-head"><img src="${path}/resources/login/images/test/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" method="post" action="/order/login/login.do" role="form">
		<input name="username" type="text" class="form-control" placeholder="用户名" required autofocus />
		<input name="password" type="password" class="form-control" placeholder="密码" required />
		<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
	</form>
</div>

</body>
</html>
