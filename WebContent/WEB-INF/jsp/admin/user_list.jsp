<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#dg').datagrid({    
	    url:'${ctx}/user?md=list',    //数据来源地址
	    columns:[[    
	        {field:'uid',title:'学生id编号',width:100},  
	        {field:'username',title:'学生姓名',width:30},
	        {field:'password',title:'学生密码',width:50},
	        {field:'code',title:'学生学号',width:100}, 
	        {field:'content',title:'是否系统管理员',width:100,formatter: function(value,row,index){
	        	if(value==1){
	        		return "是";
	        	}else{
	        		return "否";
	        	}
				
			}},
	        {field:'操作',title:'操作',width:100,align:"center",formatter:function(value,row,index){
	        	return "<a href='javascript:;' onclick='huixian(\""+row.uid+"\")'>修改</a>|<a href='javascript:;'  onclick='shanchu(\""+row.uid+"\")'>删除</a>";
	        }}    
	    ]] ,
	    fit:true,
	    fitColumns:true,
	    toolbar: [{
			iconCls: 'icon-add',
			text:"添加学生",
			handler: function(){
				//把添加对画框显示
				$("#dd").dialog("open");				
			}
		}]
	});
});

function saveUser(){
		//提交表单数据
		$('#saveForm').form('submit', {    
		    url:"${ctx}/user?md=addUser",    
		    success:function(data){  
		    	//关闭对话框
		    	$("#dd").dialog("close");
		       //服务器返回的数据
		       if("1"==data){
		    	   //成功了
		    	   parent.$.messager.show({
		    			title:'我的消息',
		    			msg:'添加分类成功',
		    			timeout:5000,
		    			showType:'slide'
		    		});
		    	   $("#dg").datagrid("reload");
		       }else{
		    	 //失败
		    	   $.messager.alert('我的消息','添加失败！');
		       }
		    }   
		    
		});  
	}; 

	

	function huixian(uid){
		//打开一个对话框 
		$("#dd1").dialog("open");	
		//对话框中的表单内容  有原来的数据
		$('#updateForm').form('load','${ctx}/user?md=huixianById&uid='+uid);
	}
	  
	  
	function updateUser(){
		//提交表单数据
		$('#updateForm').form('submit', {    
		    url:"${ctx}/user?md=update",    
		    success:function(data){  
		    	//关闭对话框
		    	$("#dd1").dialog("close");
		       //服务器返回的数据
		       if("1"==data){
		    	   //成功了
		    	   parent.$.messager.show({
		    			title:'我的消息',
		    			msg:'更新分类成功',
		    			timeout:5000,
		    			showType:'slide'
		    		});
		    	   $("#dg").datagrid("reload");
		       }else{
		    	 //失败
		    	   $.messager.alert('我的消息','更新失败！');
		       }
		    }    
		});  
	}
	//删除
	function shanchu(uid){
		//弹出确认框
		$.messager.confirm('确认对话框', '您确认要删除吗?', function(r){
			if (r){
				var url="${ctx}/user";
				var params="md=del&uid="+uid;
				//发起请求  删除分类
				$.post(url,params,function(data){
					//成功返回1
					if("1"==data){
						parent.$.messager.show({
			    			title:'我的消息',
			    			msg:'删除学生信息成功',
			    			timeout:5000,
			    			showType:'slide'
			    		});
						//重新加载数据
						$("#dg").datagrid("reload");
					}else if("2"==data){
						//失败0
						 $.messager.alert('我的消息','该学生下下存在其他关联信息不能进行删除操作!!!');
					}else{
						//失败0
						 $.messager.alert('我的消息','删除失败！');
					}
				});
			}
		});
	}	
</script>
</head>
<body>

<table id="dg" ></table>  
	<div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true"> 
         <form method="post" id="saveForm" >
         	<input type="hidden" name="uid" >
	   		<table width="100%">
	   			<tr align="center">
	   				<td>学生姓名:</td>
	   				<td><input type="text" class="easyui-textbox" name="username"><br></td>
	   			</tr>	
	   			<tr align="center">
	   				<td>学生密码:</td>
					<td><input type="text" class="easyui-textbox" name="password"><br></td>
				</tr>
				<tr align="center">
					<td>学生学号</td>
					<td><input type="text" class="easyui-textbox" name="code"><br></td>
	   			</tr>
	   			<tr align="center">
					<td>管理员权限</td>
					<td><input type="text" class="easyui-textbox" name="content"><br></td>
	   			</tr>
	   			<tr align="center">
	   				<td colspan="2">
	   					<input type="button" class="easyui-linkbutton" value="保存" onclick="saveUser()" style="width:80px"/>
	   				</td>
	   			</tr>
	   			
	   			
	   		</table>
	   	</form> 
	</div>  
	<div id="dd1" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"   
        data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true"> 
         <form method="post" id="updateForm" >
         	<input type="hidden" name="uid" >
	   		<table width="100%">
	   			<tr align="center">
	   				<td>学生姓名:</td>
	   				<td><input type="text" class="easyui-textbox" name="username"><br></td>
	   			</tr>	
	   			<tr align="center">
	   				<td>学生密码:</td>
					<td><input type="text" class="easyui-textbox" name="password"><br></td>
				</tr>
				<tr align="center">
					<td>学生学号</td>
					<td><input type="text" class="easyui-textbox" name="code"><br></td>
	   			</tr>
	   			<tr align="center">
					<td>管理员权限</td>
					<td><input type="text" class="easyui-textbox" name="content"><br></td>
	   			</tr>
	   			<tr align="center">
	   				<td colspan="2">
	   					<input type="button" class="easyui-linkbutton" value="保存" onclick="updateUser()" style="width:80px"/>
	   				</td>
	   			</tr>
	   		</table>
	   	</form> 
	</div>  
</body>
</html>