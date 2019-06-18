package aa.board11.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import aa.board11.model.BoardVO;
import aa.mybatis.MyBatisUtil;


public class BoardDao implements IBoardDao{

	@Override
	public List<BoardVO> boardList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> list = sqlSession.selectList("board.boardList");
		sqlSession.close();
		return list;
	}

	@Override
	public int updateBoard(BoardVO boardvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("board.updateBoard",boardvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
		
	}

	@Override
	public int insertBoard(BoardVO boardvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.insert("board.insertBoard",boardvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
