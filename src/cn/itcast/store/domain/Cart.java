package cn.itcast.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// 2个属性3个方法
public class Cart
{
	// 个数不确定的购物项
	private Map<String, CartItem> map = new HashMap<String, CartItem>();
	// 总计/积分
	private double total;

	// 添加购物项到购物车
	// 用户点击加入购物车按钮，传入当前商品的id和数量
	// 服务端是根据商品的id查询到商品信息得到Product对象，于是就有了CartItem对象
	public void addCartItemToCart(CartItem cartItem)
	{
		String pid = cartItem.getProduct().getPid();
		
		// 判断之前有没有买过这个商品
		// 如果没有买过，直接加
		// 如果买过就要先获取原先购买的数量，再加上本次购买的数量
		if (map.containsKey(pid))
		{
			CartItem old = map.get(pid);
			old.setNum(old.getNum() + cartItem.getNum());
		}
		else {
			map.put(pid, cartItem);
		}
	}

	// 移除购物项
	public void removeCartItem(String pid)
	{
		map.remove(pid);
	}

	// 清空购物车
	public void clearCart()
	{
		map.clear();
	}

	public Map<String, CartItem> getMap()
	{
		return map;
	}

	public void setMap(Map<String, CartItem> map)
	{
		this.map = map;
	}

	public double getTotal()
	{
		total = 0;
		Collection<CartItem> values = map.values();
		for (CartItem cartItem : values)
		{
			total += cartItem.getSubTotal();
		}
		
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}
	
	public Collection<CartItem> getCartItems()
	{
		return map.values();
	}
	
}
