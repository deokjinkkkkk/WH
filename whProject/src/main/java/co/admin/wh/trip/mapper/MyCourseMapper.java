package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseVO;

public interface MyCourseMapper {

	List<MyCourseVO> myCourseList();

	int titleInsert(MyCourseVO vo); // 코스명 등록
	
	MyCourseVO detailSelect(MyCourseVO vo); // 상세보기 페이지
	
	int myCourseDelete(MyCourseVO vo); // 나만의 코스 목록 삭제
	
	int myCouSeqUpdate(MyCourseVO vo); // 목록 삭제 시 순서 번호 업데이트
	
	//List<MyCourseVO> myCouSharing(); // 나만의 코스 공유...test

}
