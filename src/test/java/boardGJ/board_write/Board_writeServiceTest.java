package boardGJ.board_write;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import aa.board_write.model.BoardWriteVO;
import aa.board_write.service.BoardWriteService;
import aa.board_write.service.IBoardWriteService;

public class Board_writeServiceTest {

	IBoardWriteService service = new BoardWriteService();
	
	@Test
	public void test() {
		/***Given***/
		
		
		/***When***/
		

		/***Then***/
	}
	
	@Test
	public void boardwriteList() {
		/***Given***/
		int boardId = 1;
		
		/***When***/
		List<BoardWriteVO> list = service.boardwriteList(boardId);

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
		int cnt = service.boardWriteCnt(boardId);

		/***Then***/	
		assertEquals(cnt, 22);
	}
	
	@Test
	public void insertBw() {
		/***Given***/
		BoardWriteVO vo = new BoardWriteVO(1, "brown", "제목이다", "내용이다");
		
		/***When***/
		int cnt = service.insertBw(vo);

		/***Then***/		
		assertEquals(1, cnt);
	}
	
	@Test
	public void boardWriteDetail() {
		/***Given***/
		int writeId=2;
		
		/***When***/
		BoardWriteVO vo = service.boardWriteDetail(writeId);

		/***Then***/
		assertEquals(writeId, vo.getWriteId());
	}
	
	@Test
	public void deleteBw() {
		/***Given***/
		int writeId = 2;
		
		/***When***/
		int cnt = service.deleteBw(writeId);

		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void bwRevice() {
		/***Given***/
		BoardWriteVO vo = new BoardWriteVO(1, "brown", "제목이다", "내용이다");
		
		/***When***/
		int cnt = service.bwRevice(vo);

		/***Then***/		
		assertEquals(1, cnt);
	}
	
	
	@Test
	public void replyBw() {
		/***Given***/
		BoardWriteVO vo = new BoardWriteVO(2, "brown", 1, "답글", "답글입니다");
		
		/***When***/
		int cnt = service.replyBw(vo);

		/***Then***/		
		assertEquals(cnt, 1);
				
	}
	
	@Test
	public void maxWriteId() {
		/***Given***/
		
		
		/***When***/
		int num = service.maxWriteId();

		/***Then***/		
		assertEquals(num, 33);
		
	}
	
	@Test
	public void updategroupseq() {
		/***Given***/
		int writeId = 3;
		
		/***When***/
		int cnt = service.updategroupseq(writeId);

		/***Then***/		
		assertEquals(cnt, 1);
	}
}
