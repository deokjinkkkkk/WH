package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.notice.mapper.NoticeMapper;
import co.admin.wh.notice.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired 
	private NoticeMapper mapper;
	

	@Override
	public List<NoticeVO> getNoticeList() {
		return mapper.getNoticeList();
	}

	@Override
	public NoticeVO getNoticeSelect(NoticeVO vo) {
		return mapper.getNoticeSelect(vo);
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		return mapper.noticeInsert(vo);
	}

	

}
