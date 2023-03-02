package co.admin.wh.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.trip.mapper.TripMapper;
import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TripServiceImpl implements TripService{
	
	private TripMapper tripMapper;

	@Autowired
	public TripServiceImpl(TripMapper tripMapper) {
		this.tripMapper = tripMapper;
	}
	
	// api DB 등록
	@Override
	public void insertInfo(TripVO tripVO) {
		tripMapper.insertInfo(tripVO);
	}

	// 페이징
	@Override
	public List<TripVO> tripList(TripSearchVO svo) {
		return tripMapper.tripList(svo);
	}

	// 페이지 수 관리
	@Override
	public int getCountTotla(TripSearchVO svo) {
		return tripMapper.getCountTotla(svo);
	}

	@Override
	public TripVO detailSelect(TripVO tvo) {
		// 상세페이지 보기
		return tripMapper.detailSelect(tvo);
	}

	
}
