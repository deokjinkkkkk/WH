package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

public interface TripService {
	
	List<TripVO> tripList(TripSearchVO svo); //페이징

	// api DB 저장 
	void insertInfo(TripVO tripVO) throws IOException, ParserConfigurationException, SAXException;
	
	int getCountTotla(TripSearchVO svo); // 페이지수 관리

	TripVO detailSelect(TripVO vo); //상세보기 페이지
	
	List<TripVO> myCouNameSelect(TripVO vo); // 여행지 담기 버튼 클릭 -> 코스명 목록
		
	List<Map<String, Object>> selfsearch(Map<String, Object> paramMap) throws Exception; // 여행지이름 검색어 자동완성
	
	
	// 정렬
	List<TripVO> latestList(TripSearchVO vo); // 최신순 정렬
	List<TripVO> tripRegion(TripSearchVO tvo); // 지역 정렬
	List<TripVO> tripGoodRatingList(TripSearchVO vo); // 좋아요순 정렬
	List<TripVO> mainTripList(TripVO vo); // 메인페이지 사진
	
	// 검색
}
