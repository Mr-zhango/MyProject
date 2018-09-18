/**
 * @author zhangyang
 *
 */
package cn.myfreecloud.store.service;

import java.sql.SQLException;

import cn.myfreecloud.store.domain.PageBean;
import cn.myfreecloud.store.domain.User;

public interface UserService {
	//用户	通过id查询用户的方法  	 返回一个user类型的对象
	User findById(String id) throws SQLException;
	//用户	注册方法			返回值为空
	
	void regist(User user) throws Exception;
	
	//用户	登录方法			登录方法,返回一个User对象
	User login(String username, String password) throws Exception;

	PageBean<User> findByPage(int pageNumber, int pageSize)throws Exception;

	String list() throws Exception;

	void save(User user)throws Exception;

	void del(String uid) throws Exception;

	void update(User user) throws Exception;

}
