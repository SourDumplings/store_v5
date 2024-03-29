package cn.itcast.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.User;

public interface OrderService
{

	void saveOrder(Order order) throws SQLException;

	PageModel findMyOrdersWithPage(User user, int curNum) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

	void updateOrder(Order order) throws Exception;

	List<Order> findAllOrders() throws Exception;

	List<Order> findAllOrders(String state) throws Exception;

}
