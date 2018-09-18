package cn.myfreecloud.store.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.myfreecloud.store.dao.UserDao;
import cn.myfreecloud.store.domain.PageBean;
import cn.myfreecloud.store.domain.User;
import cn.myfreecloud.store.service.UserService;
import cn.myfreecloud.store.utils.BeanFactory;
import net.sf.json.JSONArray;

public class UserServiceImpl implements UserService {
	private UserDao userDao = BeanFactory.getInstance(UserDao.class);
	//通过id来查询用户
	
	@Override
	public User findById(String id) throws SQLException {
		return userDao.findById(id);
	}
	
	//注册方法&附带发送激活邮件更改用户激活状态
	@Override
	public void regist(User user) throws Exception {
		//将数据保存到数据库
		userDao.save(user);
		//发送电子邮件
		String msg="恭喜你注册成为实验室一员,请登录<a href='http://localhost:8080/store/user?md=login+' >点我登录</a>";
	}
	
	//注册成功的登录方法
	@Override
	public User login(String username, String password) throws Exception {
		return userDao.findByUserNameAndPwd(username,password);
		
	}
	@Override
	public PageBean<User> findByPage(int pageNumber, int pageSize) throws Exception {
		
		PageBean<User> pageBean = new PageBean<>();
		
		//相办法 准备好五样数据
		pageBean.setPageNumber(pageNumber);
		pageBean.setPageSize(pageSize);
		
		List<User> data=userDao.findByPage((pageNumber-1)*pageSize,pageSize);
		pageBean.setData(data);
		
		int total=userDao.findTotal();
		pageBean.setTotal(total);
		
		return pageBean;
	}
	@Override
	public String list() throws SQLException {
		 List<User> list = userDao.findAll();
		//序列化list集合成为json数组
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		String result = jsonArray.toString();
		
		return result;
	}
	
	
	@Override
	public void del(String uid) throws SQLException {
		userDao.del(uid);
	}
	@Override
	public void update(User user) throws SQLException {
		userDao.update(user);
	}

	@Override
	public void save(User user) throws Exception {
			userDao.save(user);	
	}

}
