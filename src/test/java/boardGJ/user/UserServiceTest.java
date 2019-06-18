package boardGJ.user;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import aa.user.model.UserVO;
import aa.user.service.IUserService;
import aa.user.service.UserService;

public class UserServiceTest {

	IUserService service = new UserService();
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = service.getUser(userId);

		/***Then***/
		//assertEquals("brown", userVO.getUserId());
		assertEquals("브라운", userVO.getName());
	}


}
