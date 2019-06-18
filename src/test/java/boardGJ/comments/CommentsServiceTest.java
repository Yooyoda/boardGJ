package boardGJ.comments;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aa.comments.model.CommentsVO;
import aa.comments.service.CommentsService;
import aa.comments.service.ICommentsService;

public class CommentsServiceTest {

	ICommentsService service = new CommentsService();
	
	@Test
	public void test() {
		fail("Not yet implemented");
		
		
	}
	
	@Test
	public void commentsList() {
		/***Given***/
		int writeId = 2;
		
		/***When***/
		List<CommentsVO> list = service.commentsList(writeId);

		/***Then***/
		assertEquals(10, list.size());
	}
	
	@Test
	public void commentsCnt() {
		/***Given***/
		
		
		/***When***/
		int cnt = service.commentsCnt();
		

		/***Then***/
		assertEquals(3, cnt);
		
	}
	
	@Test
	public void commentsInsert() {
		/***Given***/
		CommentsVO vo = new CommentsVO(2, "brown", "반가워용", "사용");
		
		/***When***/
		int cnt = service.commentsInsert(vo);
		

		/***Then***/
		assertEquals(1, cnt);
		
	}
	
	@Test
	public void commentsDelete() {
		/***Given***/
		int comid = 1;
		
		/***When***/
		int cnt = service.commentsDelete(comid);
		

		/***Then***/
		assertEquals(1, cnt);
		
	}

}
