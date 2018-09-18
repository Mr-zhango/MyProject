<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入头部 -->
<%@ include file="../header.jsp"%>
<div class="container"
	style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/resources/images/loginbg.jpg') no-repeat;">
	<div class="row">
		<div class="col-md-7">
			<!--<img src="${pageContext.request.contextPath}/resources/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
		</div>
		<div class="col-md-5">
			<div
				style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
				<font>会员登录</font>USER LOGIN ${msg }

				<div>&nbsp;</div>
				<form class="form-horizontal"
					action="${pageContext.request.contextPath }/user" method="post">
					<input type="hidden" name="md" value="login">
					<!-- 再添加一个隐藏域,用来隐式提交链接地址 -->

					<!-- EL表达式的param对象,参数对象 能够获取请求路径里面的参数 -->
					<%-- <input type="hidden" name="xxxURL" value="${ param.xxxURL } "> --%>
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username"
								placeholder="请输入用户名" name="username">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="inputPassword3"
								placeholder="请输入密码" name="password">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" name="code" class="form-control"
								id="inputPassword3" placeholder="请输入验证码">
						</div>
						<div class="col-sm-3">
							<img src="${pageContext.request.contextPath}/checkImg"
								onclick="checkCode(this)" />
						</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox"> 自动登录
								</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
									type="checkbox"> 记住用户名
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="登录" name="submit"
								border="0"
								style="background: url('${pageContext.request.contextPath}/resources/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
 	function checkCode(obj){
 		//获取图片标签img,参数obj
 		//改变obj对象的src的属性值
 		//alert()
 		obj.src="${pageContext.request.contextPath}/checkImg?a="+new Date().getTime();
 	}
 </script>
<%@ include file="../footer.jsp" %>
