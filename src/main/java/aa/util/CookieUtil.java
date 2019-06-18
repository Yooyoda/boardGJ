package aa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	
	public static String cookieString; // 분석할 쿠키 문자열
	
	/**
	 * 
	* Method : setCookieString
	* 작성자 : PC09
	* 변경이력 :
	* @param cookieString
	* Method 설명 : 분석할 쿠키 문자열을 받는다
	 */
	public static void setCookieString(String cookieString) {
		CookieUtil.cookieString = cookieString;
		
	}

	/**
	 * 
	* Method : getCookie
	* 작성자 : PC09
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 : 쿠키 문자열에서 특정 쿠키 값을 가져온다
	 */
	public static String getCookie(String cookie) {
		//"userId=brown; rememberme=true; test=testValue"
		//cookie="userId"
		
		//cookieArray[0] = "userId=brown"
		//cookieArray[1] = "rememberme=true"
		//cookieArray[2] = "test=testValue"
		
		String[] cookieArray = cookieString.split("; ");
		String cookieValue = "";
		
		for(String str : cookieArray) {
			logger.debug("str : {}",str);
			if(str.startsWith(cookie+"=")) { //cookie로 시작하는 값 찾기
				String[] cookieStr = str.split("="); // = 앞의 값들
				cookieValue = cookieStr[1];
				
				break;
			}
		}
		
		return cookieValue;
	}

	
	
	
}
