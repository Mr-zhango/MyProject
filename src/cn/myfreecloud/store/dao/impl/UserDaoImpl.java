package cn.myfreecloud.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.myfreecloud.store.dao.UserDao;
import cn.myfreecloud.store.domain.User;
import cn.myfreecloud.store.utils.DataSourceUtil;
//暂时只是实现了保存+更新方法
public class UserDaoImpl implements UserDao {
	//通过用户的id查找用户
	@Override
	public User findById(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user where uid=?";
		
		return qr.query(sql, new BeanHandler<>(User.class), uid);
	}
	
	//保存用户数据的方法
	@Override
	public void save(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="insert into user values(?,?,?,?,?)";
		qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getCode(),user.getContent());
	}
	//通过用户的激活码查找用户
	@Override
	public User findByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user where code=?";
		return qr.query(sql, new BeanHandler<>(User.class), code);
	}
	/*
	 * 更新用户信息
	 */		
	@Override
	public void update(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="update user set uid= ?,username=?,password=?,code=?,content=? where uid=?";
		System.out.println(user.getContent());
		qr.update(sql,user.getUid(),user.getUsername(),user.getPassword(),user.getCode(),user.getContent(),user.getUid());
	}
	//通过用户名和密码查找用户
	@Override
	public User findByUserNameAndPwd(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<>(User.class), username,password);
	}
	@Override
	public List<User> findByPage(int startindex, int pageSize) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user limit ?,?";
		return qr.query(sql, new BeanListHandler<>(User.class),startindex,pageSize);
	}
	@Override
	public int findTotal() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select count(*) from user";
		return ((Long) qr.query(sql, new ScalarHandler())).intValue();
	}
	@Override
	public List<User> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="select * from user";
		return qr.query(sql, new BeanListHandler<>(User.class));
	}
	@Override
	public void del(String uid) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtil.getDataSource());
		String sql="delete from user where uid=?";
		qr.update(sql, uid);
	}
	
}
