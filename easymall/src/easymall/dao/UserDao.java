package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.pojo.User;

@Mapper
@Repository
public interface UserDao {
	public User login(User user);
	public User checkUsername(String username);
	public int regist(User user);
	public int updatecode(User user);
}
