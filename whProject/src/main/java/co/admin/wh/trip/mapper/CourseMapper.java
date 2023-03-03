package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;

public interface CourseMapper {
	
	List<CourseVO> courseList(); // 전체조회(페이징
	
	void insertInfo(CourseVO courseVO); // api DB 저장
	
//	int getCountTotal(CourseSearchVO svo); // 페이지수 관리
//	
//	CourseVO detailSelect(CourseVO vo); //상세보기 페이지
	
}
