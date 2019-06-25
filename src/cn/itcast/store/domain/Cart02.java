package cn.itcast.store.domain;

import java.util.ArrayList;
import java.util.List;

// 2个属性3个方法
public class Cart02
{
	// 个数不确定的购物项
	private List<CartItem> list = new ArrayList();
	// 总计/积分
	private double total;

	// 添加购物项到购物车
	// 用户点击加入购物车按钮，传入当前商品的id和数量
	// 服务端是根据商品的id查询到商品信息得到Product对象，于是就有了CartItem对象
	public void addCartItemToCart(CartItem cartItem)
	{
		// 判断之前有没有买过这个商品
		// 如果没有买过，直接加
		// 如果买过就要先获取原先购买的数量，再加上本次购买的数量
		boolean flag = false;
		CartItem old = null;
		for (CartItem cartItem2 : list)
		{
			if (cartItem2.getProduct().getPid().equals(cartItem.getProduct().getPid()))
			{
				flag = true;
				old = cartItem2;
			}
		}

		if (flag == false)
		{
			list.add(cartItem);
		}
		else
		{
			old.setNum(old.getNum() + cartItem.getNum());
		}
	}

	// 移除购物项
	public void removeCartItem(String pid)
	{
		// 遍历list，看每个CartItem上的Product对象的pid是否和传入的pid相等，相等则删除
		for (CartItem cartItem : list)
		{
			if (cartItem.getProduct().getPid().equals(pid))
			{
				// 直接调用list.remove(cartItem)是无法删除的，需要通过迭代器
			}
		}
	}

	// 清空购物车
	public void clearCart()
	{
		list.clear();
	}
}
