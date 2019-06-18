package boardGJ.board11;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import aa.board11.dao.BoardDao;
import aa.board11.dao.IBoardDao;
import aa.board11.model.BoardVO;
import aa.comments.dao.CommentsDao;
import aa.comments.dao.ICommentsDao;
import aa.comments.model.CommentsVO;


public class BoardDaoTest {

	private IBoardDao dao = new BoardDao(); 
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void boardList() {
		/***Given***/
		
		
		/***When***/
		List<BoardVO> list = dao.boardList();

		/***Then***/
		assertEquals(3, list.size());
	}
	
	@Test
	public void updateBoard() {
		
		/***Given***/
		String boardName="똥강아지사랑";
		String boardUseYn = "미사용";
		int boardId = 3;
		
		BoardVO vo = new BoardVO(boardId, boardName, boardUseYn);
		/***When***/
		int cnt = dao.updateBoard(vo);
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	
	
	@Test
	public void insertBoard() throws ParseException {
		/***Given***/
		BoardVO vo = new BoardVO("iu", "똥강아지", "사용");
		/***When***/
		int cnt = dao.insertBoard(vo);
		
		/***Then***/
		assertEquals(1, cnt);
		
		
	}
	

	
	
}
