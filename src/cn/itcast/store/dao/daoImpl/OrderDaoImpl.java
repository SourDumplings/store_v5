package cn.itcast.store.dao.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;

import cn.itcast.store.dao.OrderDao;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;

public class OrderDaoImpl implements OrderDao
{

	public void saveOrder(Connection conn, Order order) throws SQLException
	{
		// TODO Auto-generated method stub
		String sql = "insert into orders values(?, ?, ?, ?, ?, ?, ?, ?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(), order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid()};
		qr.update(conn, sql, params);
	}

	public void saveOrderItem(Connection conn, OrderItem orderItem) throws SQLException
	{
		// TODO Auto-generated method stub
		String sql = "insert into orderitem values(?, ?, ?, ?, ?)";
		QueryRunner qr = new QueryRunner();
		Object[] params = {orderItem.getItemId(), orderItem.getQuantity(), orderItem.getTotal(), orderItem.getProduct().getPid(), orderItem.getOrder().getOid()};
		qr.update(conn, sql, params);
	}

}
