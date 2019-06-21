package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.service.serviceImpl.UserServiceImpl;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet
{
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return "/jsp/register.jsp";
	}

	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return "/jsp/login.jsp";
	}

	public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 清除session
		request.getSession().invalidate();
		// 重新定向到首页
		response.sendRedirect("/store_v5/index.jsp");
		return null;
	}

	// userRegist
	public String userRegist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 接收表单参数
		Map<String, String[]> map = request.getParameterMap();

		User user = new User();
		Class clazz = user.getClass();
		try
		{
			BeanUtils.populate(user, map);

			System.out.println(user);
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 遍历map中的数据
		/*
		 * for (Map.Entry<String, String[]> entry : map.entrySet()) {
		 * 
		 * }
		 */
		/*
		 * Set<String> keySet = map.keySet(); for (Iterator iterator =
		 * keySet.iterator(); iterator.hasNext();) { String str = (String)
		 * iterator.next(); String[] strs = map.get(str); System.out.println(str
		 * + ":"); for (String string : strs) { System.out.println(string); }
		 * System.out.println(); }
		 */
		MyBeanUtils.populate(user, map);

		// 为用户的其他数据赋值
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		System.out.println(user);

		// 请用业务层注册功能
		UserService userService = new UserServiceImpl();
		try
		{
			userService.userRegist(user);
			// 注册成功，向用户邮箱发送信息，跳到提示页面
			// 发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "用户注册成功，请激活");
			return "/jsp/info.jsp";
		}
		catch (Exception e)
		{
			// 注册失败
			request.setAttribute("msg", "用户注册失败");
			return "/jsp/info.jsp";
		}
	}

	// userLogin
	public String userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 获取用户数据（账户/密码）
		User user = new User();
		MyBeanUtils.populate(user, request.getParameterMap());
		// 调用业务层登录功能
		UserService userService = new UserServiceImpl();
		User user02 = null;
		try
		{
			// select * from user where username=? and password=?
			user02 = userService.userLogin(user);
			request.getSession().setAttribute("loginUser", user02);
			response.sendRedirect("/store_v5/index.jsp");
			return null;
		}
		catch (Exception e)
		{
			// 用户登录失败
			String msg = e.getMessage();
			System.out.println(msg);
			// 向request放入登录失败的信息
			request.setAttribute("msg", msg);
			return "/jsp/login.jsp";

		}
	}

	public String active(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			// 获取激活码
			String code = request.getParameter("code");
			// 调用业务层的激活功能
			UserService userService = new UserServiceImpl();
			boolean flag = userService.userActive(code);
			if (flag == true)
			{
				// 用户激活成功，向request放入提示信息，转发到登录页面
				request.setAttribute("msg", "用户激活成功，请登录！");
				return "/jsp/login.jsp";
			}
			else
			{
				// 用户激活失败，向request放入提示信息，转发到提示页面
				request.setAttribute("msg", "用户激活失败，请重新激活！");
				return "/jsp/info.jsp";
			}
			// 进行激活信息提示
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
