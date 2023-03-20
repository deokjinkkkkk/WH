package co.admin.wh.trip.service;

import java.util.List;
import java.util.Map;

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

	@Override
	public List<TripVO> myCouNameSelect(TripVO vo) {
		// 여행지 담기 버튼 클릭 -> 코스명 목록
		return tripMapper.myCouNameSelect(vo);
	}

	@Override
	public List<TripVO> tripNameSearchList(TripSearchVO vo) {
		// 여행지 이름 검색 리스트 출력
		return tripMapper.tripNameSearchList(vo);
	}

	@Override
	public List<Map<String, Object>> selfsearch(Map<String, Object> paramMap) throws Exception {
		// 여행지 이름 자동완성
		return tripMapper.selfsearch(paramMap);
	}

	@Override
	public List<TripVO> latestList(TripSearchVO vo) {
		// 최신순 정렬
		return tripMapper.latestList(vo);
	}

	@Override
	public List<TripVO> tripGoodRatingList(TripSearchVO vo) {
		// 좋아요순 정렬
		return tripMapper.tripGoodRatingList(vo);
	}
	
	@Override
	public List<TripVO> mainTripList(TripVO vo) {
		// TODO Auto-generated method stub
		return tripMapper.mainTripList(vo);
	}

	@Override
	public List<TripVO> tripRegionSearchList(TripSearchVO vo) {
		// 여행지 지역 검색 리스트 출력
		return tripMapper.tripRegionSearchList(vo);
	}

	@Override
	public List<TripVO> tripRegion(TripSearchVO tvo) {
		// 지역 정렬
		return tripMapper.tripRegion(tvo);
	}


	
}
