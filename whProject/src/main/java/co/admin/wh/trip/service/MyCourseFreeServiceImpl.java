package co.admin.wh.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.trip.mapper.MyCourseFreeMapper;
import co.admin.wh.trip.vo.MyCourseFreeVO;

@Service
public class MyCourseFreeServiceImpl implements MyCourseFreeService {
	
	@Autowired
	private MyCourseFreeMapper map;

	@Override
	public List<MyCourseFreeVO> myCourseTripList() {
		// 목록보기
		return map.myCourseTripList();
	}

	@Override
	public MyCourseFreeVO oneSelect(MyCourseFreeVO vo) {
		// 단건조회
		return map.oneSelect(vo);
	}

	@Override
	public int myCourseInsert(MyCourseFreeVO vo) {
		// 여행지 나만의 코스에 담기
		return map.myCourseInsert(vo);
	}
	
	
	
}
