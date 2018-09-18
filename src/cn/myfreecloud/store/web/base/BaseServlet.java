package cn.myfreecloud.store.web.base;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet{
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;
	//定义一个前缀
	private static final String PREFIX="/WEB-INF/jsp/";
	//定义一个后缀
	private static final String SUFFIX=".jsp";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取md的参数
		String md = request.getParameter("md");
		//如果提交的参数为空,跳转到主页面
		if(md==null){
			md="index";
		}
		try {
			//通过反射的方法创建对象并且获取方法
			Method method = this.getClass().getMethod(md,HttpServletRequest.class,HttpServletResponse.class );
			String path = (String) method.invoke(this, request,response);
			if(path!=null){
				if(path.startsWith("redirect:")){
					//返回重定向的路径
					//截取redirect:之后的目的地址
					String location = path.substring("redirect:".length());
					//通过截取的地址进行重定向
					//因为在表单提提交的时候浏览器地址栏的链接已经发生改变了,所以此处不能使用请求转发,只能使用重定向到登录界面
					response.sendRedirect(location+SUFFIX);
				}else if(path.startsWith("redirectx:")){
					//截取redirectx:之后的目的地址
					String location = path.substring("redirectx:".length());
					response.sendRedirect(location);
				}
				else{
					//不加前缀的请求转发方法
					if(path.startsWith("noprefix:")){
						//代表了不想要默认的转发路径了 需要 补上前缀
						request.getRequestDispatcher(path.substring("noprefix:".length())+SUFFIX).forward(request, response);
					}else{
						//使用默认路径进行转发
						request.getRequestDispatcher(PREFIX+path+SUFFIX).forward(request, response);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw  new RuntimeException();
		}
	}
	//设置重定向的主页地址
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "redirect:"+request.getContextPath()+"/index.jsp";
	}
}
