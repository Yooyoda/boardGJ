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

import aa.attachments.model.AttachmentsVO;
import aa.attachments.service.AttachmentsService;
import aa.attachments.service.IAttachmentsService;
import aa.board_write.model.BoardWriteVO;
import aa.board_write.service.BoardWriteService;
import aa.board_write.service.IBoardWriteService;
import aa.comments.model.CommentsVO;
import aa.comments.service.CommentsService;
import aa.comments.service.ICommentsService;

/**
 * Servlet implementation class DetailBoardWriteController
 */
@WebServlet("/detailbw")
public class DetailBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBoardWriteService bwService;
	private ICommentsService comService;
	private IAttachmentsService attachService;
	private static final Logger logger = LoggerFactory.getLogger(DetailBoardWriteController.class);
	
	@Override
	public void init() throws ServletException {
		bwService = new BoardWriteService();
		comService = new CommentsService();
		attachService = new AttachmentsService();
	}
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("detailbw doget()");
		
		
		request.setCharacterEncoding("utf-8");
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		List<CommentsVO> comList = comService.commentsList(writeId);
		logger.debug("comList : {}",comList);
		
		BoardWriteVO detailvo = bwService.boardWriteDetail(writeId);
		logger.debug("detailvo : {}", detailvo);
		request.setAttribute("detailvo", detailvo);
		request.setAttribute("comList", comList);
		
		//첨부파일 리스트
		List<AttachmentsVO> attachList = attachService.attachList(writeId);
		logger.debug("attachList : {}", attachList);
		request.setAttribute("attachList", attachList);
		
		request.getRequestDispatcher("/boardWrite/detailBoardWrite.jsp").forward(request, response);
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("detailbw dopost()");
		
		
	
	}

}
