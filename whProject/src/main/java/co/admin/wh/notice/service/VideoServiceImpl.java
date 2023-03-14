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
	public List<VideoVO> videoSelect(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoSelect(vo);
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
	public List<VideoVO> seoul(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.seoul(svo);
	}

	@Override
	public List<VideoVO> busan(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.busan(svo);
	}
	
	@Override
	public List<VideoVO> daegu(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.daegu(svo);
	}

	@Override
	public List<VideoVO> incheon(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.incheon(svo);
	}

	@Override
	public List<VideoVO> gwangju(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.gwangju(svo);
	}

	@Override
	public List<VideoVO> daejeon(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.daejeon(svo);
	}

	@Override
	public List<VideoVO> ulsan(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.ulsan(svo);
	}

	@Override
	public List<VideoVO> sejong(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.sejong(svo);
	}

	@Override
	public List<VideoVO> gyeonggi(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.gyeonggi(svo);
	}

	@Override
	public List<VideoVO> gangwon(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.gangwon(svo);
	}

	@Override
	public List<VideoVO> chungbuk(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.chungbuk(svo);
	}

	@Override
	public List<VideoVO> chungnam(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.chungnam(svo);
	}

	@Override
	public List<VideoVO> jeonbuk(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.jeonbuk(svo);
	}

	@Override
	public List<VideoVO> jeonnam(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.jeonnam(svo);
	}

	@Override
	public List<VideoVO> kyungbuk(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.kyungbuk(svo);
	}

	@Override
	public List<VideoVO> kyungnam(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.kyungnam(svo);
	}

	@Override
	public List<VideoVO> jeju(VideoSearchVO svo) {
		// TODO Auto-generated method stub
		return videoMapper.jeju(svo);
	}

	@Override
	public int videoUpdate(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoUpdate(vo);
	}

}
