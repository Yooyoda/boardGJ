package boardGJ.board11;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import aa.board11.model.BoardVO;
import aa.board11.service.BoardService;
import aa.board11.service.IBoardService;

public class BoardServiceTest {

	private IBoardService service = new BoardService();
	
	@Test
	public void boardList() {
		/***Given***/
		
		
		/***When***/
		List<BoardVO> list = service.boardList();

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
		int cnt = service.updateBoard(vo);
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	
	
	@Test
	public void insertBoard() throws ParseException {
		/***Given***/
		BoardVO vo = new BoardVO("iu", "똥강아지", "사용");
		/***When***/
		int cnt = service.insertBoard(vo);
		
		/***Then***/
		assertEquals(1, cnt);
		
		
	}

}
