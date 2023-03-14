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
	
	List<TripVO> tripNameSearchList(TripSearchVO vo); // 여행지 이름 검색 리스트 출력
	
	List<Map<String, Object>> selfSearch(Map<String, Object> paramMap) throws Exception; // 여행지이름 검색어 자동완성
	
}
