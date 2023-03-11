package co.admin.wh.trip.service;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseVO;

public interface MyCourseService {

	
	List<MyCourseVO> myCourseList();
	
	int titleInsert(MyCourseVO vo); // 코스명 등록
	
	MyCourseVO detailSelect(MyCourseVO vo); // 상세보기 페이지
	
}
