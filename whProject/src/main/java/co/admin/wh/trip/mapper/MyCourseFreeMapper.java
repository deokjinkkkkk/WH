package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseFreeVO;

public interface MyCourseFreeMapper {

	List<MyCourseFreeVO> myCourseTripList();
	
	MyCourseFreeVO oneSelect(MyCourseFreeVO vo);


}
