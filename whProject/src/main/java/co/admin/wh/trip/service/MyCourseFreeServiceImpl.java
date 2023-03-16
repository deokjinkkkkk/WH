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

	@Override
	public List<MyCourseFreeVO> myCourseSelect(MyCourseFreeVO vo) {
		// 나만의 코스 상세페이지 출력
		return map.myCourseSelect(vo);
	}

	@Override
	public int myCouDetailDel(MyCourseFreeVO vo) {
		// 나만의 코스 상세페이지에 담은 여행지 삭제
		return map.myCouDetailDel(vo);
	}

	@Override
	public int myCouUpdate(MyCourseFreeVO vo) {
		// 여행지 순서 수정
		return map.myCouUpdate(vo);
	}

	@Override
	public int couOrdUpdate(MyCourseFreeVO vo) {
		// 여행지 삭제 시 순서번호 수정
		return map.couOrdUpdate(vo);
	}

	@Override
	public int myCouIntroUpdate(MyCourseFreeVO vo) {
		// 소개글 업데이트
		return map.myCouIntroUpdate(vo);
	}
	
	
	
}
