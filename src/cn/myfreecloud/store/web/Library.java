package cn.myfreecloud.store.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.myfreecloud.store.domain.User;
import cn.myfreecloud.store.web.base.BaseServlet;

/**
 * Servlet implementation class Library
 */
public class Library extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		User user = currentUser(request);
		//如果用户不存在
		if(user==null){
			//首先提示用户然后让他登录去
			request.setAttribute("msg", "</br>当前没有用户,请先登录本系统 ");
			return "user/login";
		}
		return "library/virtule_lib";
	}
	
	/*
	 * 首先封装一个获取当前用户的方法
	 */
	private User currentUser(HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			//如果存在则返回session中的用户对象
			return user;
		}
		//否则没有则返回空
		return null;
	}
   
}
