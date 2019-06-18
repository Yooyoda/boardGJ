package aa.board11.controller;

import java.io.IOException;
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
import aa.user.model.UserVO;


/**
 * Servlet implementation class ReviceBoardController
 */
@WebServlet("/reviceboard")
public class ReviceBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(ReviceBoardController.class);
	
	private IBoardService boardService= new BoardService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("reviceboard doPost");
		request.setCharacterEncoding("utf-8");
		
		int boardid = Integer.parseInt(request.getParameter("boardId"));
		String boardName = request.getParameter("boardName");
		String boardUseYn = request.getParameter("use");
		
		UserVO vo= (UserVO) getServletContext().getAttribute("USER_INFO");
		List<BoardVO> boardList = (List<BoardVO>) request.getSession().getAttribute("BOARDLIST");
		BoardVO boardvo = new BoardVO(boardid, boardName, boardUseYn);
		
		int updatecnt = 0;
		updatecnt = boardService.updateBoard(boardvo);
		logger.debug("오는가 그대여");
		
		
			
		for(int i=0;i<boardList.size();i++) {
			if(boardvo.getBoardId() == boardList.get(i).getBoardId()) {
				boardList.get(i).setBoardName(boardvo.getBoardName());
				boardList.get(i).setBoardUseYn(boardvo.getBoardUseYn());
				
			}
			
		}
		
		logger.debug("게시글 수정하기");
		//request.getRequestDispatcher("/board/newBoard.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/newboard");
		
//		}else
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		
	
	
	
	}

}
