<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>安阳工学院虚拟实验室</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		//在页面加载成功时候,遍历返回分类列表,拼装标签展示
		$(function(){
			//定义链接地址
			var url = "${ctx}/category";
			//功能模块
			var params = "md=list";
			$.post(url,params,function(data){
				$.each(data,function(index,element){
					if(data!=null){
						//开始拼接li标签
						//<li><a href="product_list.htm">手机数码</a></li>
						/* 注意此处的双引号变为单引号 */
						//注意,这是一个超链接	 
						//var li = "<li><a href='product_list.htm'>"+element.cname+"</a></li>";
						var li="<li><a href='${ctx}/product?md=action&aname="+element.aname+"'>"+"</a></li>";
						$("#cateList").append(li);
					}
				});			
			//指定接收json数据	
			},"json");
		})
		</script>
	</head>
	
	</head>
	<body>
	
		<div class="container-fluid">
			<!--
            	时间：2015-12-30
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/resources/img/logo2.png" />
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/resources/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
						<c:if test="${empty user }">
							<li><a href="${ctx }/user?md=loginUI">登录</a></li>
							<li><a href="${ctx }/user?md=registUI">注册</a></li>
							<li><a href="${ctx }/admin?md=loginUI">后台管理</a></li>
						</c:if>
						<c:if test="${ not empty user }">
							<li>${user.username }欢迎您</li>
							<li><a href="${ctx }/user?md=logout">退出登录</a></li>
							<li><a href="${ctx }/admin?md=loginUI">后台管理</a></li>
						</c:if>
					</ol>
				</div>
			</div>
			<!--
            	时间：2015-12-30
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="http://localhost:8080/login/">首页</a>
							<a class="navbar-brand" href="http://www.baidu.com">其他</a>
							<a class="navbar-brand" href="${ ctx }/lib?md=index">虚拟实验室入口</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="cateList">
							<!-- 此处用来填充li标签 -->
							</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>