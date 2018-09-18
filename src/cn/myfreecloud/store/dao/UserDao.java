package cn.myfreecloud.store.dao;
import java.sql.SQLException;
import java.util.List;
import cn.myfreecloud.store.domain.User;

public interface UserDao {
	//通过ID查询的方法
	User findById(String id) throws SQLException;
	//保存用户的方法
	void save(User user) throws SQLException;
	//通过学号查询用户的方法
	User findByCode(String code) throws SQLException;
	//更新用户信息的方法
	void update(User user)throws SQLException;
	//通过用户名和密码查询用户的方法
	User findByUserNameAndPwd(String username, String password)throws SQLException;
	//分页查询用户
	List<User> findByPage(int i, int pageSize)throws SQLException;
	//查询总人数
	int findTotal() throws SQLException;
	//查询所有的学生
	List<User> findAll() throws SQLException;
	//删除学生信息
	void del(String uid) throws SQLException;
}