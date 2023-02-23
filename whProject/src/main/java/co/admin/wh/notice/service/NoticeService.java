package co.admin.wh.notice.service;

import java.util.List;

import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> getNoticeList();
	NoticeVO getNoticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	
}
