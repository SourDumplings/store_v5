package cn.itcast.store.test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.Test;

import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;
import cn.itcast.store.utils.MyBeanUtils;

public class TestMapListHandler
{
	// 根据订单的id查询每笔订单下订单项以及订单项对应的商品详情
	
	// SQL分析过程
	// select * from orderitem, product 笛卡尔积
	// select * from orderitem o, product p where o.pid = p.pid 筛选出有意义的数据
	// select * from orderitem o, product p where o.pid = p.pid and pid='5B33E491F6254A47A83FB584D5796313' 最终结果
	@Test
	public void test00() throws Exception
	{
		List<OrderItem> list2 = new ArrayList<OrderItem>();
		String sql = "select * from orderitem o, product p where o.pid = p.pid and o.oid='5B33E491F6254A47A83FB584D5796313'";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		// 由于返回的数据是来自多个表的多行数据，用MapListHandler封装数据
		List<Map<String, Object>> list = qr.query(sql, new MapListHandler());
		for (Map<String, Object> map : list)
		{
			// 输出map中的内容
			for (Map.Entry<String, Object> entry : map.entrySet())
			{
				System.out.print(entry.getKey() + ": " + entry.getValue() + "    ");
			}
			System.out.println();
			
			// quantity: 1    shop_price: 1799.0    pname: 努比亚（nubia）My 布拉格    pflag: 0    pdate: 2015-11-02    pid: 12    oid: 5B33E491F6254A47A83FB584D5796313    itemid: 5ED9E930E012490ABF9CD800DC6C14FB    total: 1799.0    pimage: products/1/c_0013.jpg    is_hot: 0    pdesc: 努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！    market_price: 1899.0    cid: 1    
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			
			// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
			
			
			// 将map中属于orderItem的数据自动填充到orderItem对象上
			BeanUtils.populate(orderItem, map);
			// 将map中属于product的数据自动填充到product对象上
			BeanUtils.populate(product, map);
			
			// 让orderItem和Product关联
			orderItem.setProduct(product);
			
			list2.add(orderItem);
			
		}
	}
	
}
