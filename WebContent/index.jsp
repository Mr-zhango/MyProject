<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 本页不在进行数据的显示了,直接通过请求准发的方式通过servlet来展示数据 -->
<%
	request.getRequestDispatcher("/user?md=index").forward(request, response);
%>