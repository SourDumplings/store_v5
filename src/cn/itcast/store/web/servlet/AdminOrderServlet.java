package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Order;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.service.serviceImpl.OrderServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet
{

	public String findOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		OrderService orderService = new OrderServiceImpl();
		String state = (String) req.getParameter("state");
		List<Order> list = null;
		if (state == null || state.equals(""))
		{
			// 获取全部订单
			list = orderService.findAllOrders();
		}
		else
		{
			list = orderService.findAllOrders(state);
		}

		// 将全部订单放入req
		req.setAttribute("allOrders", list);
		// 转发
		return "/admin/order/list.jsp";
	}
}
