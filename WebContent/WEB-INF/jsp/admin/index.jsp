<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>安阳工学院电气学院虚拟实验后台管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${pageContext.request.contextPath }/css/general.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" type="text/css" />
	
<style type="text/css">
body {
	color: white;
}
</style>

<script type="text/javascript">
 	function checkCode(obj){
 		//获取图片标签img,参数obj
 		//改变obj对象的src的属性值
 		//alert()
 		obj.src="${pageContext.request.contextPath}/checkImg?a="+new Date().getTime();
 	}
 	
 </script>
</head>
<body style="background: #278296">
	<center></center>
	<form method="post"
		action="${pageContext.request.contextPath }/admin?md=adminLogin"
		target="_parent" name='theForm' onsubmit="return validate()">
		<table cellspacing="0" cellpadding="0" style="margin-top: 100px"
			align="center">
			<tr>
				<td style="padding-left: 50px">
				<h1>安阳工学院电气学院虚拟实验后台管理中心</h1> ${msg }
					<table>
					
						<tr>
							<td>管理员姓名：</td>
							<td><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td>管理员密码：</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td>请输入验证码:</td>
							<td><input type="text" name="code" /></td>
							<td><img src="${pageContext.request.contextPath}/checkImg" onclick="checkCode(this)"/>	</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="进入管理中心" class="button" /></td>
						</tr>	
					</table>
				</td>
			</tr>
		</table>
	</form>
	<script language="JavaScript">
	</script>
</body>