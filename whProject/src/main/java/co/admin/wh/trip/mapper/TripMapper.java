package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

public interface TripMapper {
	
	List<TripVO> tripList(TripVO vo); // 전체조회(페이징)

	void insertInfo(TripVO tripVO); // api DB 저장
	
	int getCountTotla(TripSearchVO svo); // 페이지수 관리
	
	TripVO detailSelect(TripVO tvo); //상세보기 페이지
	
	
}
