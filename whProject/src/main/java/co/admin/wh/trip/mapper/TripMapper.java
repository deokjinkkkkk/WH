package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.TripVO;

public interface TripMapper {
	
	void insertInfo(TripVO tripVO);
	List<TripVO> tripList();
}
