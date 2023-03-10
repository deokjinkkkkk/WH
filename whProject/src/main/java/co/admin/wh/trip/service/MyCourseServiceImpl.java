package co.admin.wh.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.trip.mapper.MyCourseMapper;
import co.admin.wh.trip.vo.MyCourseVO;

@Service
public class MyCourseServiceImpl implements MyCourseService {
	
	@Autowired
	private MyCourseMapper map;

	@Override
	public List<MyCourseVO> myCourseList() {
		// 목록 전체조회
		return map.myCourseList();
	}

	@Override
	public int titleInsert(MyCourseVO vo) {
		// 코스명 등록
		return map.titleInsert(vo);
	}

	@Override
	public MyCourseVO detailSelect(MyCourseVO vo) {
		// 상세보기 페이지
		return map.detailSelect(vo);
	}
	
	
}
