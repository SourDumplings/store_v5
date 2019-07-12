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
}
