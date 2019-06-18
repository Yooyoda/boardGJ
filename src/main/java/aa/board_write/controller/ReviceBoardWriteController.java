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
import aa.util.PartUtil;

/**
 * Servlet implementation class ReviceBoardWriteController
 */
@WebServlet("/revicebw")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class ReviceBoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ReviceBoardWriteController.class);
    
	IBoardWriteService bwService;
	IAttachmentsService attachService;
	
	
	@Override
		public void init() throws ServletException {
		bwService = new BoardWriteService();
			attachService = new AttachmentsService();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 수정페이지에 출력해주는것
		logger.debug("revicebw doget");
		request.setCharacterEncoding("utf-8");
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		logger.debug("write_id : {}", writeId);
		BoardWriteVO detailvo = bwService.boardWriteDetail(writeId);
		
		request.setAttribute("detailvo", detailvo);
		
		//첨부파일 리스트
		List<AttachmentsVO> attachList = attachService.attachList(writeId);
		logger.debug("attachList : {}", attachList);
		request.setAttribute("attachList", attachList);
		
		request.getRequestDispatcher("/boardWrite/reviceBoardWrite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("revicebw dopost");
		
		request.setCharacterEncoding("utf-8");
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String title = request.getParameter("title");
		String writeContent = request.getParameter("smarteditor");
		List<AttachmentsVO> attachList = attachService.attachList(writeId);
		logger.debug("attachList : {}", attachList);
		request.setAttribute("attachList", attachList);
		
		
		BoardWriteVO vo = new BoardWriteVO(writeId, boardId, title, writeContent);
		
		int cnt = bwService.bwRevice(vo); //수정하기
		
		BoardWriteVO detailvo = bwService.boardWriteDetail(writeId);
		request.setAttribute("detailvo", detailvo);
		
		//파일 업로드 처리
		for(Part part : request.getParts()){
			if(part.getName().equals("file")){
				int fileInserCnt = fileUpload(part, writeId);
			}
		}
		
		//request.getRequestDispatcher("/boardWrite/detailBoardWrite.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/detailbw?boardId=" + boardId + "&writeId=" + writeId);
		

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
