package aa.comments.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.comments.model.CommentsVO;
import aa.comments.service.CommentsService;
import aa.comments.service.ICommentsService;

/**
 * Servlet implementation class DeleteCommentsController
 */
@WebServlet("/comdelete")
public class DeleteCommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ICommentsService service = new CommentsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int comId = Integer.parseInt(request.getParameter("comId"));
		int writeId = Integer.parseInt(request.getParameter("writeId")); 
		
		int cnt = service.commentsDelete(comId);
		List<CommentsVO> comList = service.commentsList(writeId);
		request.setAttribute("comList", comList);
		
		request.getRequestDispatcher("/detailbw?writeId=" + writeId).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
