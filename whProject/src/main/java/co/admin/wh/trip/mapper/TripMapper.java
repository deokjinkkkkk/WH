package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

public interface TripMapper {
	
	void insertInfo(TripVO tripVO); // api DB 저장
	
	List<TripVO> tripList(); // 관광지 전체 리스트
	
	int getTripTotla(TripSearchVO svo); // 페이징(총게시글)
	
	
}
