package cn.itcast.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.store.domain.User;

/**
 * Servlet Filter implementation class PriviledgeFilter
 */
public class PriviledgeFilter implements Filter
{

	/**
	 * Default constructor.
	 */
	public PriviledgeFilter()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		// TODO Auto-generated method stub
		// place your code here
		// 判断Session中是否存在已经登录的用户
		HttpServletRequest myReq = (HttpServletRequest) request;
		User user = (User) myReq.getSession().getAttribute("loginUser");
		// 如果存在，放行
		if (user != null)
		{
			chain.doFilter(request, response);
		}
		else
		{
			myReq.setAttribute("msg", "请用户登录之后再去访问");
			// 转入到提示页面
			myReq.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		// TODO Auto-generated method stub
	}

}
