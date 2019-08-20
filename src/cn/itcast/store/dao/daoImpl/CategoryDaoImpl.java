package cn.itcast.store.dao.daoImpl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.domain.Category;
import cn.itcast.store.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao
{

	public List<Category> getAllCats() throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

	public void addCategory(Category c) throws Exception
	{
		// TODO Auto-generated method stub
		String sql = "insert into category values(?, ?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql, c.getCid(), c.getCname());
	}

}
