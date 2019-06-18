package aa.board_write.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.attachments.model.AttachmentsVO;
import aa.attachments.service.AttachmentsService;
import aa.attachments.service.IAttachmentsService;
import aa.board_write.model.BoardWriteVO;
import aa.board_write.service.BoardWriteService;
import aa.board_write.service.IBoardWriteService;
import aa.user.model.UserVO;
import aa.util.PartUtil;

/**
 * Servlet implementation class NewBoardWriteController
 */
@WebServlet("/newbw")
@MultipartConfig(maxFileSize = 1024*1024*3, maxRequestSize = 1024*1024*15)
public class NewBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IBoardWriteService bwService;
	IAttachmentsService attachService;
	private static final Logger logger = LoggerFactory.getLogger(NewBoardWriteController.class);
	
	@Override
	public void init() throws ServletException {
		bwService = new BoardWriteService();
		attachService = new AttachmentsService();
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("newbw doGet");
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
		request.setAttribute("boardId", boardId);
		
		request.getRequestDispatcher("/boardWrite/newBoardWrite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("newbw doPost");
		
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String writeContent = request.getParameter("smarteditor");
		UserVO user = (UserVO) getServletContext().getAttribute("USER_INFO");
		logger.debug("USER_INFO : {}", user);
		String userId = user.getUserId();
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		
			
		//게시글 신규 등록
		BoardWriteVO bwvo = new BoardWriteVO(boardId, userId, title, writeContent);
		
		int cnt = bwService.insertBw(bwvo);
		logger.debug("cnt : {}",cnt);
		int writeId = bwService.maxWriteId();
		
		BoardWriteVO vo = bwService.boardWriteDetail(writeId);
		
		logger.debug("bwvo2 : {}", vo);
		logger.debug("writdId : {}", writeId);
		//request.setAttribute("detailvo", vo);
		request.setAttribute("boardId", boardId);
		request.setAttribute("writeId", writeId);
		
		//파일 업로드 처리
		//List<Part> parts = (List<Part>) request.getParts();
		for(Part part : request.getParts()){
			if(part.getName().equals("file")){
				int fileInserCnt = fileUpload(part, writeId);
			}
		}
		
		
//		request.getRequestDispatcher("/detailbw").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/detailbw?writeId=" + writeId);
		
	}
	
	private int fileUpload(Part part, int writeId) throws IOException {
		
		AttachmentsVO attachvo =null;
		int cnt = 0;
		
		if(part.getSize() > 0) {
			String contentDisposition = part.getHeader("content-disposition");
			String fileName = PartUtil.getFileName(contentDisposition);
			String ext = PartUtil.getExt(fileName);
			
			//업로드할 폴더확인
			//년도안에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지
			String uploadPath = PartUtil.getUpLoadPath();
			File uploadFolder = new File(uploadPath);
			
			if(uploadFolder.exists()){
				String path = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				
				attachvo = new AttachmentsVO(writeId, path, fileName);
				
				part.write(path);
				part.delete();
			}
			cnt = attachService.attachAdd(attachvo);
		}
		
		return cnt;
		
	}

}
