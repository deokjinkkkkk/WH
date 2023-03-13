package co.admin.wh.trip.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

public interface TripMapper {
	
	List<TripVO> tripList(TripVO vo); // 전체조회(페이징)

	void insertInfo(TripVO tripVO); // api DB 저장
	
	int getCountTotla(TripSearchVO svo); // 페이지수 관리
	
	TripVO detailSelect(TripVO tvo); //상세보기 페이지	
	
	List<TripVO> myCouNameSelect(TripVO vo); // 여행지 담기 버튼 클릭 -> 코스명 목록

	List<TripVO> tripNameSearchList(TripSearchVO vo); // 여행지 이름 검색 리스트 출력

	List<Map<String, Object>> selfSearch(Map<String, Object> paramMap) throws Exception; // 여행지이름 검색어 자동완성

}
