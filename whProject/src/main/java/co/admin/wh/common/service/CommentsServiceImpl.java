package co.admin.wh.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.CommentsMapper;
import co.admin.wh.common.vo.CommentsVO;
@Service
public class CommentsServiceImpl implements CommentsService {
	@Autowired CommentsMapper commentsMapper;
	

	@Override
	public int commentsCount(CommentsVO vo) {
		// TODO Auto-generated method stub
		return commentsMapper.commentsCount(vo);
	}

	@Override
	public int commentsInsert(CommentsVO vo) {
		// TODO Auto-generated method stub
		return commentsMapper.commentsInsert(vo);
	}

	@Override
	public List<CommentsVO> commentsList() {
		// TODO Auto-generated method stub
		return commentsMapper.commentsList();
	}

	@Override
	public int commentsUpdate(CommentsVO vo) {
		// TODO Auto-generated method stub
		return commentsMapper.commentsUpdate(vo);
	}

	@Override
	public int commentsDelete(CommentsVO vo) {
		// TODO Auto-generated method stub
		return commentsMapper.commentsDelete(vo);
	}

	@Override
	public int reCommentsDelete(CommentsVO vo) {
		// TODO Auto-generated method stub
		return commentsMapper.reCommentsDelete(vo);
	}

}
