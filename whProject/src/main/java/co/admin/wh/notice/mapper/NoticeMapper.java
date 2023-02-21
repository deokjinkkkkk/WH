package co.admin.wh.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.notice.vo.NoticeVO;

public interface NoticeMapper {

	List<NoticeVO> noticeList();
	NoticeVO noticeSelect(NoticeVO vo);
	int noticeInsert(NoticeVO vo);
	int noticeUpdate(NoticeVO vo);
	int noticeDelete(NoticeVO vo);
	
	List<NoticeVO> noticeSearch(@Param("key") String key, @Param("val") String val);
}
