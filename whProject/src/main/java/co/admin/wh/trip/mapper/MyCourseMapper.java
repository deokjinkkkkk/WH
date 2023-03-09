package co.admin.wh.trip.mapper;

import java.util.List;

import co.admin.wh.trip.vo.MyCourseVO;

public interface MyCourseMapper {

	List<MyCourseVO> myCourseList();

	int titleInsert(MyCourseVO vo); // 코스명 등록

}
