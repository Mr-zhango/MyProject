package cn.myfreecloud.store.service.impl;

import cn.myfreecloud.store.dao.AdminDao;
import cn.myfreecloud.store.domain.User;
import cn.myfreecloud.store.service.AdminService;
import cn.myfreecloud.store.utils.BeanFactory;

public class AdminServiceImpl implements AdminService{
	
	
	AdminDao adminDao = BeanFactory.getInstance(AdminDao.class);
	
	@Override
	public User login(String username, String password) throws Exception {
		
		return adminDao.login(username,password);
	}

}
