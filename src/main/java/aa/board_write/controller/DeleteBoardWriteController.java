package aa.board_write.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.board_write.model.BoardWriteVO;
import aa.board_write.service.BoardWriteService;
import aa.board_write.service.IBoardWriteService;

/**
 * Servlet implementation class DeleteBoardWriteController
 */
@WebServlet("/deletebw")
public class DeleteBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IBoardWriteService service;
	private static final Logger logger = LoggerFactory.getLogger(DeleteBoardWriteController.class);
	
	@Override
		public void init() throws ServletException {
			service = new BoardWriteService();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		List<BoardWriteVO> list = service.boardwriteList(boardId);
		request.setAttribute("bwList", list);
		request.setAttribute("boardId", boardId);
		
		int cnt = 0;
		cnt = service.deleteBw(writeId);
		logger.debug("cnt : {}", cnt);
		
		if(cnt != 0) {
			//request.getRequestDispatcher("/mainbw").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/mainbw?boardId=" + boardId);
			
		}else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		
		
		
		
		
		
	}

}
