package co.admin.wh.notice.service;

import org.springframework.beans.factory.annotation.Autowired;

import co.admin.wh.notice.mapper.VideoMapper;
import co.admin.wh.notice.vo.VideoVO;

public class VideoServiceImpl implements VideoService {
	
	@Autowired
	VideoMapper videoMapper;
	
	@Override
	public int videoInsert(VideoVO vo) {
		// TODO Auto-generated method stub
		return videoMapper.videoInsert(vo);
	}

}
