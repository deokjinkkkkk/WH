package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.notice.mapper.VideoMapper;
import co.admin.wh.notice.vo.VideoSearchVO;
import co.admin.wh.notice.vo.VideoVO;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	VideoMapper videoMapper;
	
	//인서트
	@Override
	public int videoInsert(VideoVO vo) {
		return videoMapper.videoInsert(vo);
	}
	
	//목록 출력
	@Override
	public List<VideoVO> videoSelect(VideoSearchVO svo) {
		return videoMapper.videoSelect(svo);
	}
	
	//상세페이지
	@Override
	public VideoVO videoDetail(VideoVO vo) {
		return videoMapper.videoDetail(vo);
	}
	
	//페이지 수 관리
	@Override
	public int videoTotal(VideoSearchVO svo) {
		return videoMapper.videoTotal(svo);
	}
	
	//지역 정렬
	@Override
	public List<VideoVO> region(VideoSearchVO svo) {
		return videoMapper.region(svo);
	}
	
	//업데이트
	@Override
	public int videoUpdate(VideoVO vo) {
		return videoMapper.videoUpdate(vo);
	}
	
	//조회수
	@Override
	public int videoHit(int videoCode) {
		return videoMapper.videoHit(videoCode);
	}
	
	//삭제
	@Override
	public int videoDelete(VideoVO vo) {
		return videoMapper.videoDelete(vo);
	}

}
