package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeMapper {

	List<NoticeVO> getNoticeList(NoticeSearchVO svo);
	int noticeInsert(NoticeVO vo);
	NoticeVO noticendetil(NoticeVO nvo);
	int imgInsert(ImageVO ivo);
	int noticeUpdate(NoticeVO vo); //수정int imgInsert(ImgVO ivo); //이미지 저장
	int noticeDelete(NoticeVO vo);
	
	int getCountTotal(NoticeSearchVO svo); //페이징
	int noticeHit(int noticeCode);//게시글 조회수 
	
}
