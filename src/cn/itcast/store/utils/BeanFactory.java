package cn.itcast.store.utils;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.User;

public class BeanFactory
{
	// 解析XML
	public static Object createObject(String name)
	{
		try
		{
			// 通过传递过来的name获取application.xml中name对应的class的值
			// 获取Document对象
			SAXReader reader = new SAXReader();
			// 获取application.xml文件的输入流，注意application.xml必须直接位于src目录下
			InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
			Document doc = reader.read(is);
			// 通过Document对象获取根节点beans
			Element rootElement = doc.getRootElement();
			// 通过根节点下所有的子结点bean集合
			List<Element> list = rootElement.elements();
			// 遍历集合，判断每个元素上的id的值是否和当前的name一致
			for (Element ele : list)
			{
				// ele相当于beans下的每个bean
				// 获取到当前结点下的id属性值
				String id = ele.attributeValue("id");
				if (id.equals(name))
				{
					String c = ele.attributeValue("class");
					// 利用class值通过反射创建对象返回
					Class<?> claz = Class.forName(c);
					return claz.newInstance();
				}
			}
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException
	{
		UserDao userDao = (UserDao) BeanFactory.createObject("UserDao");
		User user = new User();
		user.setUsername("aaa");
		user.setPassword("aaa");
		User uu = userDao.userLogin(user);
		System.out.println(uu);
	}
}
