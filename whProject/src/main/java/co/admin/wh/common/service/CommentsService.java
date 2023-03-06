package co.admin.wh.common.service;

import java.util.List;

import co.admin.wh.common.vo.CommentsVO;

public interface CommentsService {
	public int commentsCount(CommentsVO vo); //댓글 개수
	public int commentsInsert(CommentsVO vo); //댓글등록
	public List<CommentsVO> commentsList(); //댓글목록
	public int commentsUpdate(CommentsVO vo); //댓글 수정
	public int commentsDelete(CommentsVO vo); //댓글 삭제
	public int reCommentsDelete(CommentsVO vo);
	
}
