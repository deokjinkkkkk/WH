package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseFreeVO;

public interface MyCourseFreeMapper {

	List<MyCourseFreeVO> myCourseTripList(); // 전체출력
	
	MyCourseFreeVO oneSelect(MyCourseFreeVO vo);
	
	int myCourseInsert(MyCourseFreeVO vo); // 여행지 나만의 코스에 담기


}
