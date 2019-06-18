package aa.attachments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import aa.attachments.model.AttachmentsVO;
import aa.mybatis.MyBatisUtil;

public class AttachmentsDao implements IAttachmentsDao {

	@Override
	public List<AttachmentsVO> attachList(int writeId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<AttachmentsVO> list= sqlSession.selectList("attachments.attachList",writeId);
		sqlSession.close();
		return list;
	}

	@Override
	public int attachAdd(AttachmentsVO vo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.insert("attachments.attachAdd",vo);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public int attachDelete(int fileId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int cnt = sqlSession.delete("attachments.attachDelete",fileId);
		sqlSession.commit();
		sqlSession.close();
		return cnt;
	}

	@Override
	public AttachmentsVO attachSelect(int fileId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		AttachmentsVO vo= sqlSession.selectOne("attachments.attachSelect",fileId);
		sqlSession.close();
		return vo;
	}

}
