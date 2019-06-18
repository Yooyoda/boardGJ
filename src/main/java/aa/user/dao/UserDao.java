package aa.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.mybatis.MyBatisUtil;
import aa.user.model.UserVO;

public class UserDao implements IUserDao{

	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	
	public static void main(String[] args) {
		
		/***Given***/
		IUserDao userDao = new UserDao();
		
		/***When***/
		
		UserVO getUser = userDao.getUser("brown");
		
		/***Then***/
		logger.debug("값이 있나 없나");
				
		logger.debug("getUser : {}", getUser);
		
	}
	
	@Override
	public UserVO getUser(String userId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVO user = sqlSession.selectOne("user.getUser",userId);
		sqlSession.close();
		
		return user;
	}

}
