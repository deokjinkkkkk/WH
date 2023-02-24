package co.admin.wh.notice.mapper;

import java.util.List;



import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeMapper {

	List<NoticeVO> getNoticeList();
	NoticeVO getNoticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	NoticeVO ndetil(NoticeVO nvo);
	
}
