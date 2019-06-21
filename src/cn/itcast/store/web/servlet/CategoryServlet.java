package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.utils.JedisUtils;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet
{
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
//		 使用redis缓存，以免每次都查数据库
		// 在redis中获得全部分类信息
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if (jsonStr == null || "".equals(jsonStr))
		{
			System.out.println("缓存中没有数据。。。。");
			// 调用业务层获取全部分类
			CategoryService categoryService = new CategoryServiceImpl();
			List<Category> list = categoryService.getAllCats();
			// System.out.println(list);
			// 将全部分类转换为JSON格式的数据
			jsonStr = JSONArray.fromObject(list).toString();
			jedis.set("allCats", jsonStr);
			
		}
		JedisUtils.closeJedis(jedis);
//		
//		// 不使用redis缓存
//		CategoryService categoryService = new CategoryServiceImpl();
//		List<Category> list = categoryService.getAllCats();
//		// System.out.println(list);
//		// 将全部分类转换为JSON格式的数据
//		String jsonStr = JSONArray.fromObject(list).toString();
		
		// 将全部分类的信息响应到客户端
		// 告诉浏览器本次相应的数据是JSON格式的字符串
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonStr);

		return null;
	}

}
