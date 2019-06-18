package aa.comments.service;

import java.util.List;

import aa.comments.dao.CommentsDao;
import aa.comments.dao.ICommentsDao;
import aa.comments.model.CommentsVO;

public class CommentsService implements ICommentsService {

	private ICommentsDao commentsDao = new CommentsDao();
	
	
	@Override
	public List<CommentsVO> commentsList(int writeId) {
		List<CommentsVO> list = commentsDao.commentsList(writeId);
		return list;
	}

	@Override
	public int commentsCnt() {
		int cnt = commentsDao.commentsCnt();
		return cnt;
	}

	@Override
	public int commentsInsert(CommentsVO vo) {
		int cnt = commentsDao.commentsInsert(vo);
		return cnt;
	}

	@Override
	public int commentsDelete(int comid) {
		int cnt = commentsDao.commentsDelete(comid);
		return cnt;
	}

}
