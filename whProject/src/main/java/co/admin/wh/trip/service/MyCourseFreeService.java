package co.admin.wh.trip.service;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseFreeVO;

public interface MyCourseFreeService {

	List<MyCourseFreeVO> myCourseTripList();
	
	MyCourseFreeVO oneSelect(MyCourseFreeVO vo);
}
