package co.admin.wh.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeMapper {

	List<NoticeVO> getNoticeList();
	NoticeVO getNoticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
}
