package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import easymall.dao.UserDao;
import easymall.pojo.User;
import easymall.util.CodeUtil;
import easymall.util.MailUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public boolean checkUsername(String username) {
		User user = userDao.checkUsername(username);
		return user != null;
	}

	@Override
	public int regist(User user) {
	    int temp = userDao.regist(user);
	    if(temp > 0) {
	    	//生成激活码
		    String code = CodeUtil.generateUniqueCode();
	    	//保存成功则通过线程的方式给用户发送一封邮件，进行邮箱验证
		    new Thread(new MailUtil(user.getEmail(), code)).start();
	    }
		return temp;
	}

	@Override
	public int updatecode(User user) {
		return userDao.updatecode(user);
	}

	

}
