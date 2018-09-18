package cn.myfreecloud.store.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.myfreecloud.store.dao.AdminDao;
import cn.myfreecloud.store.domain.User;
import cn.myfreecloud.store.utils.DataSourceUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public User login(String username, String password) throws Exception {
		QueryRunner  runner = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select *from user where username=? and password=?";
		
		User user = runner.query(sql, new BeanHandler<User>(User.class), username, password);
		return user;
	}

}
