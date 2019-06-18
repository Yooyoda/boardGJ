package aa.board_write.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import aa.paging.PageVO;
import aa.user.model.UserVO;


/**
 * Servlet implementation class PagingListBoardWriteController
 */
@WebServlet("/mainbw")
public class MainBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	IBoardWriteService bwService ;
	private static final Logger logger = LoggerFactory.getLogger(MainBoardWriteController.class);
	
	@Override
	public void init() throws ServletException {
		bwService = new BoardWriteService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("mainbw doGet");
		
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		List<BoardWriteVO> bwlist =  bwService.boardwriteList(boardId);
		logger.debug("bwlist : {}", bwlist);
		
		
		String page1 = request.getParameter("page"); //page라는 파라미터의 값 가져옴
		String pageSize1 = request.getParameter("pageSize"); //pageSize라는 파라미터의 값 가져옴
		
		int page = 0;
		int pageSize = 0;
		
		if (page1 == null && pageSize1 == null){ //파라미터가 없으면
			page = 1;
			pageSize = 10;
			
		}else { //파라미터가 있으면
			page = Integer.parseInt(page1);
			pageSize = Integer.parseInt(pageSize1);
			
		}
			
		PageVO pagevo = new PageVO(page,pageSize); //페이지 객체 생성
		
		Map<String, Object> resultMap =new HashMap<String, Object>();
		resultMap.put("page", page);
		resultMap.put("pageSize",pageSize);
		resultMap.put("boardId",boardId);
		logger.debug("resultMap : {}", resultMap);
		
		Map<String, Object> result = bwService.boardWritePaging(resultMap);
		logger.debug("result:{}",result);
		
		
		request.setAttribute("bwList", result.get("bwList")); //속성을 정해줌
		request.setAttribute("paginationSize", result.get("paginationSize")); //속성을 정해줌
		request.setAttribute("pageVO", pagevo);
		request.getRequestDispatcher("/boardWrite/mainBoardWrite.jsp").forward(request, response);
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
