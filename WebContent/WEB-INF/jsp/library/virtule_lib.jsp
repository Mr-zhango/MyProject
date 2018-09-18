<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />
<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}
.carousel-inner .item img {
	width: 100%;
	height: 300px;
}
.container .row div {
	/* position:relative;
				 float:left; */
}
font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}
</style>
</head>
	<jsp:include page="../header.jsp"></jsp:include>
		<body>
			<div align="center">
			<!-- 用户已经登录 -->
				<h2>虚拟实验室入口:</h2><a href="info.jsp"><img src="resources/img/door.png"></a><br>
				<h2>下载实验指导书:</h2><a href="https://pan.baidu.com/s/1mChBIYhJlpwy6Y1s8OZJtA"><img src="resources/img/book.png"></a><br>
			</div>
		</body>
	<jsp:include page="../footer.jsp"></jsp:include>
</html>