package aa.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import aa.comments.model.CommentsVO;
import aa.mybatis.MyBatisUtil;

public class CommentsDao implements ICommentsDao {

	@Override
	public List<CommentsVO> commentsList(int writeId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<CommentsVO> list = sqlSession.selectList("comments.commentsList",writeId);
		sqlSession.close();
		return list;
	}

	@Override
	public int commentsCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.selectOne("comments.commentsCnt");
		sqlSession.close();
		return cnt;
	}

	@Override
	public int commentsInsert(CommentsVO vo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.insert("comments.commentsInsert",vo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int commentsDelete(int comid) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.update("comments.commentsDelete",comid);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

}
