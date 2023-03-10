package co.admin.wh.trip.service;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseFreeVO;

public interface MyCourseFreeService {

	List<MyCourseFreeVO> myCourseTripList(); // 전체 출력
	
	MyCourseFreeVO oneSelect(MyCourseFreeVO vo);
	
	int myCourseInsert(MyCourseFreeVO vo); // 여행지 나만의 코스에 담기
}
