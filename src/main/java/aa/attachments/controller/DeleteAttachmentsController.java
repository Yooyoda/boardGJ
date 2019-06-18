package aa.attachments.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.attachments.service.AttachmentsService;
import aa.attachments.service.IAttachmentsService;

/**
 * Servlet implementation class DeleteAttachmentsController
 */
@WebServlet("/attachdelete")
public class DeleteAttachmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(DeleteAttachmentsController.class);
	IAttachmentsService service;
	
	@Override
	public void init() throws ServletException {
		service = new AttachmentsService();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		int writeId =Integer.parseInt( request.getParameter("writeId"));
		
		int cnt = service.attachDelete(fileId);
		
		response.sendRedirect(request.getContextPath() + "/revicebw?fileId=" + fileId + "&writeId=" + writeId);
		
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		int[] fileId =new int[5];
//		
//		for(int i=0;i<5;i++) {
//			fileId[i] = 
//		}
//		
//		for(int i=0;i<5;i++) {
//			if(fileId[i] != null) {
//				
//			}
//		}
//		logger.debug("들어오는가 마는가");
//		String fileId0 = request.getParameter("fileId0");
//		String fileId1 = request.getParameter("fileId1");
//		String fileId2 = request.getParameter("fileId2");
//		String fileId3 = request.getParameter("fileId3");
//		String fileId4 = request.getParameter("fileId4");
//		
//		int boardId = Integer.parseInt(request.getParameter("boardId"));
//		int writeId = Integer.parseInt(request.getParameter("writeId"));
//		
//		int fileId=0;
//
//		logger.debug("fileId0 : {}",fileId0);
//		logger.debug("fileId0 : {}",fileId1);
//		logger.debug("fileId0 :"
//				+ " {}",fileId2);
//		logger.debug("fileId0 : {}",fileId3);
//		logger.debug("fileId0 : {}",fileId4);
//		
//		int cnt=0;
//		if(fileId0!=null) {
//			fileId = Integer.parseInt(fileId0);
//			
//		}
//		
////		if(fileId1!=null) {
////			fileId = fileId1;
////			
////		}
////		
////		if(fileId2!=null) {
////			fileId = fileId2;
////			
////		}
////		
////		if(fileId3!=null) {
////			fileId = fileId3;
////			
////		}
////		
////		if(fileId4!=null) {
////			fileId = fileId4;
////		}
//		
//		cnt = service.attachDelete(fileId);
//		
//		request.setAttribute("boardId", boardId);
//		request.setAttribute("writeId", writeId);
//		
//		logger.debug("deleteattachcnt : {}", cnt);
//		
//		request.getRequestDispatcher("/revicebw?writeId=" + writeId ).forward(request, response);
	}

}
