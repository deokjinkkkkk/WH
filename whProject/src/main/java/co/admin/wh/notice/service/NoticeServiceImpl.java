package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.NoticeMapper;

import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired 
	private NoticeMapper mapper;
	

	@Override
	public List<NoticeVO> getNoticeList(NoticeSearchVO svo) {
		return mapper.getNoticeList(svo);
	}


	@Override
	public int noticeInsert(NoticeVO vo) {
		return mapper.noticeInsert(vo);
	}

	@Override
	public NoticeVO noticendetil(NoticeVO nvo) {
		return mapper.noticendetil(nvo);
	}


	@Override
	public int getCountTotal(NoticeSearchVO svo) {
		return mapper.getCountTotal(svo);
	}



	@Override
	public int noticeUpdate(NoticeVO vo) {
		return mapper.noticeUpdate(vo);
	}


	@Override
	public int noticeDelete(NoticeVO vo) {
		return mapper.noticeDelete(vo);
	}


	@Override
	public int noticeHit(int noticeCode) {
		return mapper.noticeHit(noticeCode);
	}


	@Override
	public int imgInsert(ImageVO ivo) {
		return mapper.imgInsert(ivo);
	}


	


	


	




	
	

}
