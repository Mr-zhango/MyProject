package cn.myfreecloud.store.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import cn.myfreecloud.store.domain.PageBean;
import cn.myfreecloud.store.domain.User;
import cn.myfreecloud.store.service.UserService;
import cn.myfreecloud.store.utils.BeanFactory;
import cn.myfreecloud.store.utils.UUIDUtil;
import cn.myfreecloud.store.web.base.BaseServlet;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	private UserService userService=BeanFactory.getInstance(UserService.class);
	
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {
			System.out.println("已经进行请求转发");
			return "index2";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询首页信息出问题了 ");
			return "info";
		}
	}
	
	/**
	 * 请求转发到 注册页面
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "user/register";
	}
	/**
	 * 真实的注册逻辑
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//获取提交数据
			Map<String, String[]> parameterMap = request.getParameterMap();
			//封装一个user对象
			User user = new User();
			BeanUtils.populate(user, parameterMap);
			user.setUid(UUIDUtil.getId());
			//调用service完成注册逻辑
			userService.regist(user);
//			没有异常
//			操作成功了
//				需要给用户一个提示页面 提示注册成功
			request.setAttribute("msg", "恭喜你注册成功,请登录");
			return "info";
		} catch (Exception e) {
			e.printStackTrace();
//			有异常
//			操作失败了
//				给用户一个提示页面 请重新注册
			request.setAttribute("msg", "注册失败了,请重新注册");
			return "info";
		}
	}
	
	/*
	 * 登陆界面
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "user/login";
	}
	
	/**
	 * 登录逻辑
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取账号密码
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		String code = request.getParameter("code");
		System.out.println(code);
		try {
				//将账号密码作为参数 传递给service 返回user
				HttpSession session = request.getSession();
				// 把object类型的session转化为String类型的session
				String session_code = (String) session.getAttribute("checkcode_session");
				//验证码正确执行校验逻辑
				if(session_code.equalsIgnoreCase(code)){
				//将账号密码作为参数 传递给service 返回user
				User user=userService.login(username,password);
				//判断user是否为空
				if(user==null){
					//不匹配
					//request.setAttribute("msg", "账号密码不匹配");
					//return "noprefix:/login";
					request.getSession().setAttribute("msg", "账号密码不匹配");
					return "user/login";
				}else{
					//保存用户状态
					request.getSession().setAttribute("user", user);
					return "index2";
				}
			}else{
				request.getSession().setAttribute("msg", "验证码错误");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("msg", "服务器异常请重试");
			return "user/login";
		}
	}
	/**
	 * 退出登录
	 * 销毁session
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		return "redirect:"+request.getContextPath()+"/index";
	}
	
	
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		//easyui传参
		//page---->pageNumber
		try {
			//rows----->pageSize
			int pageNumber = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("rows"));
			//接收 到参数以后 调用service 分页查询 
			PageBean<User> pb=userService.findByPage(pageNumber, pageSize);
			
			//要求返回的数据格式
			/**
			 * {
					total:总条数,--------pageBean的total
					rows:[{数据对象,.....}]---pageBean中的data
				}
			 */
			
			HashMap<String, Object> result = new HashMap<>();
			Object total=pb.getTotal();
			result.put("total", total);
			Object rows=pb.getData();
			result.put("rows", rows);
			//写回去
			
			/*
			 * map集合转换为json类型的数据
			 */
			JSONObject fromObject = JSONObject.fromObject(result);
			response.getWriter().print(fromObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 查询分页信息
	 */
	
	public String list(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//解决回写数据中文乱码问题
		//在filter里面进行转码
		
		//response.setContentType("text/html;charset=utf-8");
		//前端的ajax请求,要求返回分类信息列表,要求的是json格式的数据
		String json;
		try {
			//把获取到的数据回写到网页
			json = userService.list();
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//如果产生异常就回写空的字符串
			response.getWriter().print("");
		}
		//既不是重定向也不是请求转发,返回值为null
		return null;
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		String username = request.getParameter("username");
		System.out.println(username);
		String password = request.getParameter("password");
		System.out.println(password);
		String code = request.getParameter("code");
		
		String content = request.getParameter("content");
		System.out.println(content);
		//封装对象
		User user = new User();
		//设置分类的id
		user.setUid(UUIDUtil.getId());
		user.setUsername(username);
		user.setPassword(password);
		user.setCode(code);
		user.setContent(content);
	try {
			userService.save(user);
			//如果成功了就回写1
			response.getWriter().print("1");
		} catch (Exception e) {
			e.printStackTrace();
			//操作失败了 
			//回写0
			response.getWriter().print("0");
		}
	}
	
	public void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//首先获取分类的名称
		//获取cname值
		String uid = request.getParameter("uid");
		System.out.println(uid);
		
		try {
			//调用service保存分类
			userService.del(uid);
			//如果成功了就回写1
			response.getWriter().print("1");
		} catch (Exception e) {
			e.printStackTrace();
			//操作失败了产生了其他的异常写0
			//回写0
			response.getWriter().print("0");
		}
	}
	
	public String huixianById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//因为回显的数据带有中文,所以我们需要进行编码
		response.setContentType("text/html;charset=utf-8");
		//首先获取分类的名称
		//获取cname值
		String uid = request.getParameter("uid");
		System.out.println(uid);
	try {
			//调用service保存分类
		User User =  userService.findById(uid);
			//如果成功了就转换json格式 输出在浏览器显示
			response.getWriter().print(JSONObject.fromObject(User).toString());
		} catch (Exception e) {
			e.printStackTrace();
			//操作失败了 
			//回写0
			request.setAttribute("msg", "小伙子逗我呢?");
			return "info";
		}
	return null;
	}
	
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//首先获取分类的名称
		//获取uid值
		String uid = request.getParameter("uid");
		System.out.println("uid:"+uid);
		String username = request.getParameter("username");
		System.out.println("username:"+username);
		String password = request.getParameter("password");
		System.out.println("password:"+password);
		String code = request.getParameter("code");
		System.out.println("code:"+code);
		String content = request.getParameter("content");
		
		System.out.println("content"+content);
		
		//封装对象
		User user = new User();
		//设置学生的id
		user.setUid(uid);
		user.setUsername(username);
		user.setPassword(password);
		user.setCode(code);
		user.setContent(content);
	try {
			//调用service保存分类
			userService.update(user);
			//如果成功了就回写1
			response.getWriter().print("1");
		} catch (Exception e) {
			e.printStackTrace();
			//操作失败了 
			//回写0
			response.getWriter().print("0");
		}
	}
	
	
}
