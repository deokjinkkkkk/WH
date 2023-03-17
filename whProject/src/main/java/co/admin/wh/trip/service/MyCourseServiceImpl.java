package co.admin.wh.trip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.tag.mapper.TagMapper;
import co.admin.wh.tag.service.TagService;
import co.admin.wh.trip.mapper.MyCourseMapper;
import co.admin.wh.trip.vo.MyCourseVO;

@Service
public class MyCourseServiceImpl implements MyCourseService {
	
	@Autowired
	private MyCourseMapper map;
	@Autowired
	private TagMapper tmap;
	@Autowired
	private TagService tservice;


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

	@Override
	public int myCourseDelete(MyCourseVO vo) {
		// 나만의 코스 목록 삭제
		return map.myCourseDelete(vo);
	}

	@Override
	public int myCouSeqUpdate(MyCourseVO vo) {
		// 목록 삭제 시 순서 번호 업데이트
		return map.myCouSeqUpdate(vo);
	}

	@Override
	public int myCouStateUpdate(MyCourseVO vo) {
		// 코스 공유 상태값
		return map.myCouStateUpdate(vo);
	}

	@Override
	public int myCouIntroUpdate(MyCourseVO vo) {
		// 소개글 업데이트
		//=====tag 적용시키기!~!!!!====
		tservice.saveTag( "", 1);
		return map.myCouIntroUpdate(vo);
	}

	
}

