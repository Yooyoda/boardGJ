package aa.board11.service;

import java.util.ArrayList;
import java.util.List;

import aa.board11.dao.BoardDao;
import aa.board11.dao.IBoardDao;
import aa.board11.model.BoardVO;


public class BoardService implements IBoardService {

	private IBoardDao boarddao = new BoardDao();
	
	@Override
	public List<BoardVO> boardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		list = boarddao.boardList();
		
		return list;
	}

	@Override
	public int updateBoard(BoardVO boardvo) {

		return boarddao.updateBoard(boardvo);
	}

	@Override
	public int insertBoard(BoardVO boardvo) {
		
		return boarddao.insertBoard(boardvo);
	}

}
