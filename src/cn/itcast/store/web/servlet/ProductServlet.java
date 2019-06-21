package cn.itcast.store.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	public String findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 获取商品pid
		String pid = req.getParameter("pid");
		// 根据商品pid查询商品信息
		ProductService productService = new ProductServiceImpl();
		Product product = productService.findProductByPid(pid);
		// 将获取到的商品放入request
		req.setAttribute("product", product);
		// 转发到/jsp/product_info_jsp
		return "/jsp/product_info.jsp";
	}

}
