package cn.itcast.store.service.serviceImpl;

import java.util.List;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.utils.BeanFactory;
import cn.itcast.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService
{
	CategoryDao categoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");
	
	public List<Category> getAllCats() throws Exception
	{
		// TODO Auto-generated method stub
		return categoryDao.getAllCats();
	}

	public void addCategory(Category c) throws Exception
	{
		// TODO Auto-generated method stub
		// 本质是向MYSQL中插入一条数据
		categoryDao.addCategory(c);
		// 更新redis缓存，否则前端从redis拿到的分类信息不更新
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

}
