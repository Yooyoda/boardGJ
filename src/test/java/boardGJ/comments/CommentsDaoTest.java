package boardGJ.comments;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aa.board11.model.BoardVO;
import aa.comments.dao.CommentsDao;
import aa.comments.dao.ICommentsDao;
import aa.comments.model.CommentsVO;

public class CommentsDaoTest {

	ICommentsDao dao = new CommentsDao();
	
	@Test
	public void test() {
		fail("Not yet implemented");
		/***Given***/
		
		
		/***When***/
		

		/***Then***/
	}
	
	@Test
	public void commentsList() {
		/***Given***/
		int writeId = 2;
		
		/***When***/
		List<CommentsVO> list = dao.commentsList(writeId);

		/***Then***/
		assertEquals(10, list.size());
	}
	
	@Test
	public void commentsCnt() {
		/***Given***/
		
		
		/***When***/
		int cnt = dao.commentsCnt();
		

		/***Then***/
		assertEquals(3, cnt);
		
	}
	
	@Test
	public void commentsInsert() {
		/***Given***/
		CommentsVO vo = new CommentsVO(2, "brown", "반가워용", "사용");
		
		/***When***/
		int cnt = dao.commentsInsert(vo);
		

		/***Then***/
		assertEquals(1, cnt);
		
	}
	
	@Test
	public void commentsDelete() {
		/***Given***/
		int comid = 1;
		
		/***When***/
		int cnt = dao.commentsDelete(comid);
		

		/***Then***/
		assertEquals(1, cnt);
		
	}
	
	
	
	
	
	
	
	
	

}
