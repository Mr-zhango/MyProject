package cn.myfreecloud.store.web.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 *	添加过滤路径
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//首先进项强转
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		//从session中获得user对象
		Object user = req.getSession().getAttribute("user");
		//获取当前访问的路径
		StringBuffer path = req.getRequestURL();
		System.out.println("当前访问的路径:"+path);
		//获取请求参数
		String queryString = req.getQueryString();
		
		System.out.println("请求参数:"+queryString);
		
		path.append("?").append(queryString);
		String encode = URLEncoder.encode(path.toString());
		System.out.println("拼接后的路径:"+path);
		if(user==null){
			
			resp.sendRedirect(req.getContextPath()+"/login.jsp?xxxURL="+encode);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
