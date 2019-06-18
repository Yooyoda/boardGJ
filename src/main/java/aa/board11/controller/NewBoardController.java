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
 * Servlet implementation class NewBoardController
 */
@WebServlet("/newboard")
public class NewBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IBoardService boardService;
	private static final Logger logger = LoggerFactory.getLogger(NewBoardController.class);
	
	@Override
		public void init() throws ServletException {
			boardService = new BoardService();
		}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BoardVO> boardList = boardService.boardList();
		
		request.setAttribute("boardList", boardList);
		
		
//		UserVO user =  (UserVO) request.getSession().getAttribute("USER_INFO");
//		logger.debug("USER_INFO.userId : {}",user.getName());
		
		request.getRequestDispatcher("/board/newBoard.jsp").forward(request, response);
	
	}
	
	//게시판 생성
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String boardName = request.getParameter("nboardName");
		String use = request.getParameter("nuse");
		logger.debug("boardName : {}",boardName);
		UserVO vo= (UserVO) getServletContext().getAttribute("USER_INFO");
		
		logger.debug("USER_INFO : {}",vo);
		BoardVO boardvo = new BoardVO(vo.getUserId(), boardName, use);
		
		List<BoardVO> boardList = (List<BoardVO>) request.getSession().getAttribute("BOARDLIST");
		
		
		int insertcnt = 0;
		insertcnt = boardService.insertBoard(boardvo);
		
		if(insertcnt != 0 && vo != null) {
			boardList.add(boardvo);
			request.getRequestDispatcher("/board/newBoard.jsp").forward(request, response);
		}	
//		}else
//			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		
	}

}
