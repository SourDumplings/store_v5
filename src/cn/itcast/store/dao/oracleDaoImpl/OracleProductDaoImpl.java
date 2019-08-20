package cn.itcast.store.dao.oracleDaoImpl;

import java.util.List;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;

public class OracleProductDaoImpl implements ProductDao
{

	public List<Product> findHots() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> findNews() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Product findProductByPid(String pid) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int findTotalRecords(String cid) throws Exception
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int findTotalRecords() throws Exception
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Product> findAllProductsWithPage(int currentPageNum, int pageSize) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void saveProduct(Product product) throws Exception
	{
		// TODO Auto-generated method stub

	}

}
