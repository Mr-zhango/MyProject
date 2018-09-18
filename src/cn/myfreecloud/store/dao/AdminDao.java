package cn.myfreecloud.store.dao;

import cn.myfreecloud.store.domain.User;

public interface AdminDao {

	User login(String username, String password) throws Exception;

}
