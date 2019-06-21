package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// TODO Auto-generated method stub
		// 调用业务层的两个功能，查询最新商品和查询最热商品
		ProductService productService = new ProductServiceImpl();
		List<Product> list1 = productService.findHots();
		List<Product> list2 = productService.findHots();
		// 将两个集合放在request里面
		req.setAttribute("hots", list1);
		req.setAttribute("news", list2);
		// 转发到真实的首页上
		return "/jsp/index.jsp";
	}
      

}
