package aa.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.board11.model.BoardVO;
import aa.board11.service.BoardService;
import aa.board11.service.IBoardService;
import encrypt.kisa.sha256.KISA_SHA256;
import aa.user.model.UserVO;
import aa.user.service.IUserService;
import aa.user.service.UserService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IUserService userService = new UserService();
	IBoardService boardService= new BoardService();
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String encryptPass = KISA_SHA256.encrypt(pass);
		
		UserVO user = userService.getUser(userId);
		logger.debug("uservo : {}", user);
		
		if(user != null) {
			if(encryptPass.equals(user.getPass())) {
				
				List<BoardVO> boardlist = boardService.boardList();
				
				
				request.getSession().setAttribute("BOARDLIST", boardlist);
				getServletContext().setAttribute("USER_INFO", user);
				
				logger.debug("로그인 성공");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
				
			}else {
				logger.debug("로그인 실패");
				request.getRequestDispatcher("/login/login.jsp").forward(request, response);
			}
	
		}
	
	}

}
