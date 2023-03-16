package co.admin.wh.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.TourMapper;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.common.vo.TourVO;

@Service
public class TourServiceImpl implements TourSerivce{
	
	@Autowired TourMapper tourMapper;

	@Override
	public int tourInsert(TourVO vo) {
		// TODO Auto-generated method stub
		return tourMapper.tourInsert(vo);
	}

	@Override
	public List<TourVO> tourList() {
		// TODO Auto-generated method stub
		return tourMapper.tourList();
	}

	@Override
	public int imgInsert(ImageVO ivo) {
		// TODO Auto-generated method stub
		return tourMapper.imgInsert(ivo);
	}

	@Override
	public int tourUpdate(TourVO vo) {
		// TODO Auto-generated method stub
		return tourMapper.tourUpdate(vo);
	}

	@Override
	public int tourAdminInsert(TourVO vo) {
		// TODO Auto-generated method stub
		return tourMapper.tourAdminInsert(vo);
	}
	
}
