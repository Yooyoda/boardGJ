package aa.attachments.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aa.attachments.model.AttachmentsVO;
import aa.attachments.service.AttachmentsService;
import aa.attachments.service.IAttachmentsService;

/**
 * Servlet implementation class DownloadAttachmentsController
 */
@WebServlet("/attachDown")
public class DownloadAttachmentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAttachmentsService service;
    
	@Override
		public void init() throws ServletException {
			service = new AttachmentsService();
		}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int fileId = Integer.parseInt(request.getParameter("fileId"));
		int writeId = Integer.parseInt(request.getParameter("writeId"));
		
		
		AttachmentsVO attachVo =  service.attachSelect(fileId);
		

		// 파일 업로드된 경로
		String savePath = attachVo.getFilePath();

		// 실제 내보낼 파일명
		String orgfilename = attachVo.getOriginalFileName();

		InputStream in = null;
		OutputStream os = null;
		File file = null;
		boolean skip = false;
		String client = "";


		try{


			// 파일을 읽어 스트림에 담기
			try{
				file = new File(savePath);
				in = new FileInputStream(file);
			}catch(FileNotFoundException fe){
				skip = true;
			}

			client = request.getHeader("User-Agent");

			// 파일 다운로드 헤더 지정
			response.reset() ;
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Description", "JSP Generated Data");


			if(!skip){


				// IE
				if(client.indexOf("MSIE") != -1){
					response.setHeader ("Content-Disposition", "attachment; filename="+new String(orgfilename.getBytes("KSC5601"),"ISO8859_1"));

				}else{
					// 한글 파일명 처리
					orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");

					response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
					response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
				} 

				response.setHeader ("Content-Length", ""+file.length() );



				os = response.getOutputStream();
				byte b[] = new byte[(int)file.length()];
				int leng = 0;

				while( (leng = in.read(b)) > 0 ){
					os.write(b,0,leng);
				}
			}else{
				response.setContentType("text/html;charset=UTF-8");
			}

			in.close();
			os.close();

		}catch(Exception e){
			e.printStackTrace();
		}
		
		//response.sendRedirect(request.getContextPath()+"/detailbw?writeId=" + writeId);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
