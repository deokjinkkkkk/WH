package co.admin.wh.trip.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.trip.TripVO;

public interface TripMapper {
	
	void insertInfo(TripVO tripVO);
	List<TripVO> tripList();
}
