package cn.itcast.store.service.serviceImpl;

import java.util.List;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.dao.daoImpl.ProductDaoImpl;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;

public class ProductServiceImpl implements ProductService
{
	private ProductDao productDao = new ProductDaoImpl();
	
	
	public List<Product> findNews() throws Exception
	{
		// TODO Auto-generated method stub
		return productDao.findNews();
	}

	public List<Product> findHots() throws Exception
	{
		// TODO Auto-generated method stub
		return productDao.findHots();
	}

	public Product findProductByPid(String pid) throws Exception
	{
		// TODO Auto-generated method stub
		return productDao.findProductByPid(pid);
	}

}
