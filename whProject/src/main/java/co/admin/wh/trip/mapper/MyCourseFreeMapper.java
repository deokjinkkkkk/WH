package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseFreeVO;

public interface MyCourseFreeMapper {

	List<MyCourseFreeVO> myCourseTripList(); // 전체출력
	
	MyCourseFreeVO oneSelect(MyCourseFreeVO vo);
	
	int myCourseInsert(MyCourseFreeVO vo); // 여행지 나만의 코스에 담기
	
	List<MyCourseFreeVO> myCourseSelect(MyCourseFreeVO vo); // 나만의 코스 상세페이지 출력
	
	int myCouDetailDel(MyCourseFreeVO vo); // 나만의 코스 상세페이지에 담은 여행지 삭제

	int myCouUpdate(MyCourseFreeVO vo); // 여행지 순서 수정
	
}
