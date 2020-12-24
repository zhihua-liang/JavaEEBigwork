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
	    	//���ɼ�����
		    String code = CodeUtil.generateUniqueCode();
	    	//����ɹ���ͨ���̵߳ķ�ʽ���û�����һ���ʼ�������������֤
		    new Thread(new MailUtil(user.getEmail(), code)).start();
	    }
		return temp;
	}

	@Override
	public int updatecode(User user) {
		return userDao.updatecode(user);
	}

	

}
