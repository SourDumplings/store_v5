package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	// findAllCats
	public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 获取全部分类信息
		CategoryService categoryService = new CategoryServiceImpl();
		List<Category> lists = categoryService.getAllCats();
		// 将全部分类信息录入request
		req.setAttribute("allCats", lists);
		// 转发到/admin/category/list.jsp
		
		return "/admin/category/list.jsp";
	}
	
	public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{		
		return "/admin/category/add.jsp";
	}
	
	public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 获取分类的名称
		String cname = req.getParameter("cname");
		// 创建分类的id
		String cid = UUIDUtils.getId();
		Category c = new Category();
		c.setCid(cid);
		c.setCname(cname);
		// 调用业务层功能
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.addCategory(c);
		// 重定向到查询全部分类的信息
		resp.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllCats");
		return null;
	}
}
