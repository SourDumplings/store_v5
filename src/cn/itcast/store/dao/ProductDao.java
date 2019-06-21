package cn.itcast.store.dao;

import java.util.List;

import cn.itcast.store.domain.Product;

public interface ProductDao
{

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;


}
