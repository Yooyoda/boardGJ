package boardGJ.board_write;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aa.board_write.dao.BoardWriteDao;
import aa.board_write.dao.IBoardWriteDao;
import aa.board_write.model.BoardWriteVO;

public class Board_writeDaoTest {

	IBoardWriteDao dao = new BoardWriteDao();
	@Test
	public void test() {
		fail("Not yet implemented");
		/***Given***/
		
		
		/***When***/
		

		/***Then***/
	}

	@Test
	public void boardwriteList() {
		/***Given***/
		int boardId = 1;
		
		/***When***/
		List<BoardWriteVO> list = dao.boardwriteList(boardId);

		/***Then***/	
		assertEquals(12, list.size());
	}
	
	
	@Test
	public void boardWritePaging() {
		/***Given***/
		
		
		/***When***/
		

		/***Then***/		
		
		
	}
	
	
	@Test
	public void boardWriteCnt() {
		/***Given***/
		int boardId = 1;
		
		/***When***/
		int cnt = dao.boardWriteCnt(boardId);

		/***Then***/	
		assertEquals(cnt, 22);
	}
	
	@Test
	public void insertBw() {
		/***Given***/
		BoardWriteVO vo = new BoardWriteVO(1, "brown", "제목이다", "내용이다");
		
		/***When***/
		int cnt = dao.insertBw(vo);

		/***Then***/		
		assertEquals(1, cnt);
	}
	
	@Test
	public void boardWriteDetail() {
		/***Given***/
		int writeId=2;
		
		/***When***/
		BoardWriteVO vo = dao.boardWriteDetail(writeId);

		/***Then***/
		assertEquals(writeId, vo.getWriteId());
	}
	
	@Test
	public void deleteBw() {
		/***Given***/
		int writeId = 2;
		
		/***When***/
		int cnt = dao.deleteBw(writeId);

		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void bwRevice() {
		/***Given***/
		BoardWriteVO vo = new BoardWriteVO(1, "brown", "제목이다", "내용이다");
		
		/***When***/
		int cnt = dao.bwRevice(vo);

		/***Then***/		
		assertEquals(1, cnt);
	}
	
	
	@Test
	public void replyBw() {
		/***Given***/
		BoardWriteVO vo = new BoardWriteVO(2, "brown", 1, "답글", "답글입니다");
		
		/***When***/
		int cnt = dao.replyBw(vo);

		/***Then***/		
		assertEquals(cnt, 1);
				
	}
	
	@Test
	public void maxWriteId() {
		/***Given***/
		
		
		/***When***/
		int num = dao.maxWriteId();

		/***Then***/		
		assertEquals(num, 33);
		
	}
	
	@Test
	public void updategroupseq() {
		/***Given***/
		int writeId = 3;
		
		/***When***/
		int cnt = dao.updategroupseq(writeId);

		/***Then***/		
		assertEquals(cnt, 1);
				
		
	}
}
