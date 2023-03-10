package co.admin.wh.notice.service;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> getNoticeList(NoticeSearchVO svo);
	int noticeInsert(NoticeVO vo);
	NoticeVO noticendetil(NoticeVO vo);
	int noticeUpdate(NoticeVO vo); //수정
	int imgInsert(ImageVO ivo);
	int noticeDelete(NoticeVO vo);
	int getCountTotal(NoticeSearchVO svo); //페이징
	
	int noticeHit(int noticeCode);//게시글 조회수 
}
