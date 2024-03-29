package cn.itcast.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.User;

public interface OrderDao
{

	void saveOrder(java.sql.Connection conn, Order order) throws SQLException;

	void saveOrderItem(java.sql.Connection conn, OrderItem orderItem) throws SQLException;

	int getTotalRecords(User user) throws SQLException;

	List<Order> findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

	void updateOrder(Order order) throws Exception;

	List<Order> findAllOrders() throws Exception;

	List<Order> findAllOrders(String state) throws Exception;

}
