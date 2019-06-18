package boardGJ.attachments;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import aa.attachments.dao.AttachmentsDao;
import aa.attachments.dao.IAttachmentsDao;
import aa.attachments.model.AttachmentsVO;

public class AttachmentsDaoTest {

	private IAttachmentsDao dao = new AttachmentsDao();
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void attachAdd() {
		/***Given***/
		AttachmentsVO vo = new AttachmentsVO(3, "file경로1", "원본파일명1");
		
		/***When***/
		int cnt = dao.attachAdd(vo);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	@Test
	public void attachList() {
		/***Given***/
		List<AttachmentsVO> list ;
		int writeId = 2;
		/***When***/
		list = dao.attachList(writeId);
		
		/***Then***/
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void attachDelete() {
		/***Given***/
		int fileId = 1;
		/***When***/
		int cnt = dao.attachDelete(fileId);
		
		
		/***Then***/
		assertEquals(cnt, 1);
		
	}
	
	@Test
	public void attachSelect() {
		/***Given***/
		int fileId = 1;
		
		/***When***/
		AttachmentsVO vo = dao.attachSelect(fileId);
		
		/***Then***/
		assertEquals(fileId, vo.getFileId());
		
	}

}
