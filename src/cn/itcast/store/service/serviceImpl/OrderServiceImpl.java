package cn.itcast.store.service.serviceImpl;

import java.sql.SQLException;

import java.sql.Connection;

import cn.itcast.store.dao.OrderDao;
import cn.itcast.store.dao.daoImpl.OrderDaoImpl;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService
{

	public void saveOrder(Order order) throws SQLException
	{
		// TODO 保存订单和订单下所有的订单项（同时成功或者失败）
		/*
		 * try { JDBCUtils.startTransaction(); OrderDao orderDao = new
		 * OrderDaoImpl(); orderDao.saveOrder(order); for (OrderItem orderItem :
		 * order.getList()) { orderDao.saveOrderItem(orderItem); }
		 * JDBCUtils.commitAndClose(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace();
		 * JDBCUtils.rollbackAndClose(); }
		 */

		Connection conn = null;
		try
		{
			// 获取连接
			conn = JDBCUtils.getConnection();
			// 开启事务
			conn.setAutoCommit(false);
			// 保存订单
			// 传入conn是为了保证用的同一个连接
			OrderDao orderDao = new OrderDaoImpl();
			orderDao.saveOrder(conn, order);
			// 保存订单项
			for (OrderItem orderItem : order.getList())
			{
				orderDao.saveOrderItem(conn, orderItem);
			}
			// 提交
			conn.commit();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			conn.rollback();
		}
/*		finally
		{
			if (conn != null)
			{
				conn.close();
				conn = null;
			}
		}*/
	}

}
