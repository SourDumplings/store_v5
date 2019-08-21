package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Order;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.service.serviceImpl.OrderServiceImpl;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

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

	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 服务端获取到订单ID
		String oid = req.getParameter("id");
		// 查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
		OrderService orderService = new OrderServiceImpl();
		Order order = orderService.findOrderByOid(oid);
		// 将返回的集合转换为JSON格式字符串,响应到客户端
		String jsonStr = JSONArray.fromObject(order.getList()).toString();
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().println(jsonStr);
		return null;
	}
	
	public String updateOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 服务端获取到订单ID
		String oid = req.getParameter("oid");
		// 查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
		OrderService orderService = new OrderServiceImpl();
		Order order = orderService.findOrderByOid(oid);
		// 设置订单的状态，修改订单
		order.setState(3);
		orderService.updateOrder(order);
		// 重定向到查询已发货订单
		resp.sendRedirect("/store_v5/AdminOrderServlet?method=findOrders&state=3");
		return null;
	}
}
