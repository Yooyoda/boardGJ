package aa.board_write.controller;

import java.io.File;
import java.io.IOException;
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
 * Servlet implementation class ReplyBoardWriteController
 */
@WebServlet("/replybw")
@MultipartConfig(maxFileSize = 1024*1024*3, maxRequestSize = 1024*1024*15)
public class ReplyBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IBoardWriteService service = new BoardWriteService();
	private IAttachmentsService attachService = new AttachmentsService();
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyBoardWriteController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
	
		request.setAttribute("writeId", writeId);
		request.setAttribute("boardId", boardId);
		
		request.getRequestDispatcher("/boardWrite/replyBoardWrite.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int parentId = Integer.parseInt(request.getParameter("writeId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		UserVO user = (UserVO) getServletContext().getAttribute("USER_INFO");
		String userId = user.getUserId();
		String title = request.getParameter("title");
		String writeContent = request.getParameter("smarteditor");
		BoardWriteVO bwvo = new BoardWriteVO(boardId, userId, parentId, title, writeContent);
		int cnt = service.replyBw(bwvo);
		
		int writeId = service.maxWriteId();
		int cnt1 = service.updategroupseq(parentId);
		
		logger.debug("cnt : {}", cnt);
		
		//파일 업로드 처리
				//List<Part> parts = (List<Part>) request.getParts();
				for(Part part : request.getParts()){
					if(part.getName().equals("file")){
						int fileInserCnt = fileUpload(part, writeId);
					}
				}
		
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
