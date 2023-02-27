package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeMapper {

	List<NoticeVO> getNoticeList(NoticeSearchVO svo);
	int noticeInsert(NoticeVO vo);
	NoticeVO noticendetil(NoticeVO nvo);
	int noticeUpdate(NoticeVO vo); //수정
	int noticeDelete(NoticeVO vo);
	
	int getCountTotal(NoticeSearchVO svo); //페이징
	int noticeCount(int noticeCode);//게시글 카운트 
	
}
