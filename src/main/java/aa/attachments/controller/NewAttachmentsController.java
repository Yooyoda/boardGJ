package aa.attachments.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import aa.attachments.model.AttachmentsVO;
import aa.attachments.service.AttachmentsService;
import aa.attachments.service.IAttachmentsService;
import aa.util.PartUtil;

/**
 * Servlet implementation class NewAttachmentsController
 */
@WebServlet("/attachAdd")
public class NewAttachmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IAttachmentsService attachService;
	
	@Override
	public void init() throws ServletException {
		attachService = new AttachmentsService();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//첨부파일
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		Part profile = request.getPart("profile");
		
		AttachmentsVO attachvo = new AttachmentsVO();
		
		String contentDisposition = profile.getHeader("content-disposition");
		String filename = PartUtil.getFileName(contentDisposition);
		String ext = PartUtil.getExt(filename);
		
		String uploadPath = PartUtil.getUpLoadPath();
		File uploadFolder = new File(uploadPath);
		
		if(uploadFolder.exists()) {
			//파일 디스크에 쓰기
			String filePath =  uploadPath + File.separator + UUID.randomUUID().toString() +  ext;
			attachvo.setFilePath(filePath);
			attachvo.setOriginalFileName(filename);
			attachvo.setWriteId(writeId);
			
			profile.write(filePath);
			profile.delete();
		}
		
		int insertcnt = attachService.attachAdd(attachvo);
	
		//response.sendRedirect(request.getContextPath()+"/detailbw?boardId=" + boardId + "&writeId=" + writeId);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
