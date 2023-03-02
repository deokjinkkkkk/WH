package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.CourseVO;

public interface CourseMapper {

	List<CourseVO> courseList(CourseVO vo); // 전체조회(페이징)
	
	void insertInfo(CourseVO courseVO); // api DB 저장
}
