package aa.board_write.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aa.board_write.model.BoardWriteVO;
import aa.mybatis.MyBatisUtil;
import aa.paging.PageVO;

public class BoardWriteDao implements IBoardWriteDao {
	private static final Logger logger = LoggerFactory.getLogger(BoardWriteDao.class);

	@Override
	public List<BoardWriteVO> boardwriteList(int boardId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardWriteVO> list = sqlSession.selectList("boardwrite.bwList", boardId);
		sqlSession.close();
		return list;
	}

	@Override
	public List<BoardWriteVO> boardWritePaging(Map<String, Object> resultMap) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardWriteVO> list = sqlSession.selectList("boardwrite.bwPagingList", resultMap);
		sqlSession.close();

		return list;
	}

	@Override
	public int boardWriteCnt(int boardId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.selectOne("boardwrite.bwCnt",boardId);
		logger.debug("cnt:{}", cnt);
		sqlSession.close();
		return cnt;
	}

	@Override
	public int insertBw(BoardWriteVO bwvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.insert("boardwrite.bwInsert",bwvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public BoardWriteVO boardWriteDetail(int writeId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardWriteVO vo = sqlSession.selectOne("boardwrite.bwDetail",writeId);
		sqlSession.close();
		
		return vo;
	}

	@Override
	public int deleteBw(int writeId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("boardwrite.bwDelete",writeId);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int bwRevice(BoardWriteVO bwvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("boardwrite.bwRevice", bwvo);
		sqlSession.commit();
		sqlSession.close();
		
		return cnt;
	}

	@Override
	public int replyBw(BoardWriteVO bwvo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.insert("boardwrite.bwReply",bwvo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int maxWriteId() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int max = sqlSession.selectOne("boardwrite.maxWriteId");
		sqlSession.close();
		return max;
	}

	@Override
	public int updategroupseq(int writeId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("boardwrite.updategroupseq", writeId);
		sqlSession.commit();
		sqlSession.close();
		
		return cnt;
	}



}
