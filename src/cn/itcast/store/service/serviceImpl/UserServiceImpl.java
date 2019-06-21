package cn.itcast.store.service.serviceImpl;

import java.sql.SQLException;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImpl.UserDaoImpl;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;

public class UserServiceImpl implements UserService
{

	public void userRegist(User user) throws SQLException
	{
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		userDao.userRegist(user);
	}

	public boolean userActive(String code) throws SQLException
	{
		// TODO Auto-generated method stub
		// 实现注册功能
		UserDao userDao = new UserDaoImpl();
		// 对DB发送select * from user where code = ?
		User user = userDao.userActive(code);

		if (user != null)
		{
			// 能根据激活码查询到一个用户
			user.setState(1);
			// 修改用户状态，清空激活码
			user.setCode(null);
			// 对数据库中执行一次真实的更新操作
			// update user set username=?, password=?, name=?, email=?,
			// telephone=?, birthday=?, sex=?, state=?, code=? where uid=?
			userDao.updateUser(user);
			return true;
		}
		else
		{
			// 不可以根据激活码查到一个用户
			return false;
		}
	}

	public User userLogin(User user) throws SQLException
	{
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		User loginUser = userDao.userLogin(user);
		// 此处也可以利用异常来在模块之间传递数据
		if (loginUser == null)
		{
			throw new RuntimeException("用户不存在或密码有误！");
		}
		else if (loginUser.getState() == 0)
		{
			throw new RuntimeException("用户未激活！");
			
		}
		else
		{
			return loginUser;
		}
	}

}
