package aa.comments.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.comments.model.CommentsVO;
import aa.comments.service.CommentsService;
import aa.comments.service.ICommentsService;
import aa.user.model.UserVO;

/**
 * Servlet implementation class InsertCommentsController
 */
@WebServlet("/comInsert")
public class InsertCommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ICommentsService service = new CommentsService();
	private static final Logger logger = LoggerFactory.getLogger(InsertCommentsController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		UserVO user = (UserVO) getServletContext().getAttribute("USER_INFO");
		String userId = user.getUserId();
		String comContents = request.getParameter("comcontents");
		
		CommentsVO comvo = new CommentsVO(writeId, userId, comContents, "사용");
		logger.debug("comvo : {}", comvo);
		
		logger.debug("comvo : {}",comvo);
		
		int cnt = service.commentsInsert(comvo);
		logger.debug("comments cnt : {}", cnt);
		
		//request.getRequestDispatcher("/detailbw?boardId=" + boardId + "writeId" + writeId).forward(request, response);
		response.sendRedirect(request.getContextPath()+"/detailbw?boardId=" + boardId + "&writeId=" + writeId);
	}

}
