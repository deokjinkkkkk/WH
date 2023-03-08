package co.admin.wh.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.TourMapper;
import co.admin.wh.common.vo.tourVO;

@Service
public class TourServiceImpl implements TourSerivce{
	@Autowired TourMapper tourMapper;

	@Override
	public int tourInsert(tourVO vo) {
		// TODO Auto-generated method stub
		return tourMapper.tourInsert(vo);
	}
	
	
}
