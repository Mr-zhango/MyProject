package cn.myfreecloud.store.service;

import cn.myfreecloud.store.domain.User;

public interface AdminService {

	User login(String username, String password) throws Exception;
	
}
