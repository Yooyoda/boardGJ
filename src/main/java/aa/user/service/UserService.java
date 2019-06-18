package aa.user.service;

import aa.user.dao.IUserDao;
import aa.user.dao.UserDao;
import aa.user.model.UserVO;

public class UserService implements IUserService{

	private IUserDao userDao = new UserDao();
	
	@Override
	public UserVO getUser(String userId) {
		
		UserVO user;
		user = userDao.getUser(userId);
		
		return user;
	}

}
