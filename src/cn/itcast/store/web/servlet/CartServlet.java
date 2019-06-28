package cn.itcast.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Cart;
import cn.itcast.store.domain.CartItem;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet
{
	private static final long serialVersionUID = 1L;

	// 添加购物项到购物车
	public String addCartItemToCart(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 从session获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		if (cart == null)
		{
			// 如果获取不到,创建购物车对象,放在session中
			cart = new Cart();
			req.getSession().setAttribute("cart", cart);
		}

		// 获取到商品id,数量
		String pid = req.getParameter("pid");
		int num = Integer.parseInt(req.getParameter("quantity"));
		// 通过商品id查询都商品对象
		ProductService productService = new ProductServiceImpl();
		Product product = productService.findProductByPid(pid);
		// 获取到待购买的购物项
		CartItem cartItem = new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);
		// 调用购物车上的方法
		cart.addCartItemToCart(cartItem);
		// 重定向到/jsp/cart.jsp
		// 这里不能用转发，否则页面URL没变，页面刷新一次就会转发一次，购物车里就会增加商品，即会出现数据重复提交问题
		resp.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
	}

	// 删除购物项到购物车
	public String removeCartItem(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 获取待删除的商品id
		String pid = req.getParameter("pid");
		// 获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// 调用购物车的清空方法
		cart.removeCartItem(pid);
		// 重定向到/jsp/cart.jsp页面
		resp.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
	}

	// 清空购物车
	public String clearCart(HttpServletRequest req, HttpServletResponse resp) throws Exception
	{
		// 获取购物车
		Cart cart = (Cart) req.getSession().getAttribute("cart");
		// 调用购物车的清空方法
		cart.clearCart();
		// 重定向到/jsp/cart.jsp页面
		resp.sendRedirect("/store_v5/jsp/cart.jsp");
		return null;
	}
}
