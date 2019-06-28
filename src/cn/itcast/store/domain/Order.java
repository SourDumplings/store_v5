package cn.itcast.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order
{
	private String oid;
	private Date ordertime;
	private double total;
	private int state;
	private String address;
	private String name;
	private String telephone;

	// private String uid;
	// 1.程序对象和对象发生关系，而不是对象和对象的属性发生关系
	// 2.设计Order目的是携带订单上的数据向service和dao传递，user对象可以携带更多的数据
	private User user;

	// 程序在查询订单的同时，我们还需要查询订单下所有的订单项
	private List<OrderItem> list = new ArrayList<OrderItem>();

	public String getOid()
	{
		return oid;
	}

	public void setOid(String oid)
	{
		this.oid = oid;
	}

	public Date getOrdertime()
	{
		return ordertime;
	}

	public void setOrdertime(Date ordertime)
	{
		this.ordertime = ordertime;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public int getState()
	{
		return state;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public List<OrderItem> getList()
	{
		return list;
	}

	public void setList(List<OrderItem> list)
	{
		this.list = list;
	}
	
	
}
