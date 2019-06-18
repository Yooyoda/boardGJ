package aa.attachments.service;

import java.util.List;

import aa.attachments.dao.AttachmentsDao;
import aa.attachments.dao.IAttachmentsDao;
import aa.attachments.model.AttachmentsVO;

public class AttachmentsService implements IAttachmentsService{

	private IAttachmentsDao dao = new AttachmentsDao();
	
	@Override
	public List<AttachmentsVO> attachList(int writeId) {
		List<AttachmentsVO> list = dao.attachList(writeId);
		return list;
	}

	@Override
	public int attachAdd(AttachmentsVO vo) {
		int cnt = dao.attachAdd(vo);
		return cnt;
	}

	@Override
	public int attachDelete(int fileId) {
		int cnt = dao.attachDelete(fileId);
		return cnt;
	}

	@Override
	public AttachmentsVO attachSelect(int fileId) {
		AttachmentsVO vo = dao.attachSelect(fileId);
		return vo;
	}

}
