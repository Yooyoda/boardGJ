package boardGJ.attachments;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aa.attachments.model.AttachmentsVO;
import aa.attachments.service.AttachmentsService;
import aa.attachments.service.IAttachmentsService;

public class AttachmentsServiceTest {

	IAttachmentsService service;
	
	
	@Test
	public void attachList() {
		
		/***Given***/
		service = new AttachmentsService();
		int writeId = 2;
		
		/***When***/
		List<AttachmentsVO> list = service.attachList(writeId);

		/***Then***/
		//assertEquals("file경로", list.get(0).getFilepath());
	}

	@Test
	public void attachAdd() {
		
		/***Given***/
		service = new AttachmentsService();
		AttachmentsVO vo = new AttachmentsVO(87, "file경로1", "원본파일명1");
		
		/***When***/
		int cnt = service.attachAdd(vo);
		
		/***Then***/
		assertEquals(1, cnt);
		
	}
	
	@Test
	public void attachDelete() {
		/***Given***/
		int fileId = 1;
		/***When***/
		int cnt = service.attachDelete(fileId);
		
		
		/***Then***/
		assertEquals(cnt, 1);
		
	}
	
	@Test
	public void attachSelect() {
		/***Given***/
		int fileId = 1;
		
		/***When***/
		AttachmentsVO vo = service.attachSelect(fileId);
		
		/***Then***/
		assertEquals(fileId, vo.getFileId());
		
	}
	
	
}
