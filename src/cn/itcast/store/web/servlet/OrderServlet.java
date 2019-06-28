package cn.itcast.store.web.servlet;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Cart;
import cn.itcast.store.domain.CartItem;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.service.serviceImpl.OrderServiceImpl;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {
	// 保存订单
	public String saveOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 确认用户的登录状态
		User user = (User)req.getSession().getAttribute("loginUser");
		if (user == null)
		{
			req.setAttribute("msg", "请登录后再下单");
			return "/jsp/info.jsp";
		}
		// 获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// 创建订单对象，为订单对象赋值
		Order order = new Order();
		order.setOid(UUIDUtils.getId());
		order.setOrdertime(new Date());
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setUser(user);
		// 便利购物车的同时，创建订单项
		for (CartItem cartItem : cart.getCartItems())
		{
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(UUIDUtils.getId());
			orderItem.setQuantity(cartItem.getNum());
			orderItem.setTotal(cartItem.getSubTotal());
			orderItem.setProduct(cartItem.getProduct());
			
			// 设置订单和订单项的关联，从程序的角度体现订单和订单项的对应关系
			orderItem.setOrder(order);
			order.getList().add(orderItem);
		}
		
		// 调用业务层功能：保存订单
		OrderService orderService = new OrderServiceImpl();
		orderService.saveOrder(order);
		// 清空购物车
		cart.clearCart();
		// 将订单放入request
		req.setAttribute("order", order);
		// 转发/jsp/order_info.jsp
		return "/jsp/order_info.jsp";
	}
}
