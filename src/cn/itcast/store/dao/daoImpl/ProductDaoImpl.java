package cn.itcast.store.dao.daoImpl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao
{

	public List<Product> findHots() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0, 9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public List<Product> findNews() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "select * from product where pflag=0 order by pdate desc limit 0, 9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	public Product findProductByPid(String pid) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "select * from product where pid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return (Product) qr.query(sql, new BeanHandler<Product>(Product.class), pid);
	}

	public int findTotalRecords(String cid) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "select count(*) from product where cid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long) qr.query(sql, new ScalarHandler(), cid);
		return num.intValue();
	}

	public List<Product> findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "select * from product where cid=? limit ?, ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class), cid, startIndex, pageSize);
	}
	
	

}
