package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.TripVO;

public interface CourseMapper {
	
	List<CourseVO> courseList(CourseVO vo); // 전체조회(페이징)
	
	void insertInfo(CourseVO courseVO); // api DB 저장
	
	int getCountTotal(CourseSearchVO svo); // 페이지수 관리
	
	List<CourseVO> detailSelect(CourseVO vo); //상세보기 페이지
	
	CourseVO oneSelect(CourseVO vo);// 코스 단건조회
	
	List<CourseVO> myCouSharing(CourseVO vo); // 나만의 코스 공유
	
	List<CourseVO> tripCourseRegion(CourseSearchVO cvo); //지역별 정렬
	
}
