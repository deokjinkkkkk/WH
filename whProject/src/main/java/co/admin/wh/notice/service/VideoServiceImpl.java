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
	
	@Override
	public int videoInsert(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoInsert(vo);
	}

	@Override
	public List<VideoVO> videoSelect(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.videoSelect(svo);
	}

	@Override
	public VideoVO videoDetail(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoDetail(vo);
	}

	@Override
	public int videoTotal(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.videoTotal(svo);
	}
	
	@Override
	public List<VideoVO> region(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.region(svo);
	}

	

	@Override
	public int videoUpdate(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoUpdate(vo);
	}

	@Override
	public int videoHit(int videoCode) {
		// TODO Auto-generated method stub
		return videoMapper.videoHit(videoCode);
	}

	@Override
	public int videoDelete(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoDelete(vo);
	}

}
