package co.admin.wh.trip.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.trip.mapper.CourseMapper;
import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.TripVO;

@Service
public class CourseServiceImpl implements CourseService{

	private CourseMapper courseMapper;
	
	@Autowired
	public CourseServiceImpl(CourseMapper courseMapper) {
		this.courseMapper = courseMapper;
	}	

	@Override
	public void insertInfo(CourseVO courseVO) {
		// api DB 등록
		courseMapper.insertInfo(courseVO);
		
	}

	@Override
	public List<CourseVO> courseList(CourseSearchVO svo) {
		// 페이징
		return courseMapper.courseList(svo);
	}

	@Override
	public int getCountTotal(CourseSearchVO svo) {
		// 페이지 수 관리
		return courseMapper.getCountTotal(svo);
	}


	@Override
	public List<CourseVO> detailSelect(CourseVO vo) {
		// 상세페이지 보기
		return courseMapper.detailSelect(vo);
	}

	@Override
	public CourseVO oneSelect(CourseVO vo) {
		// 단건조회
		return courseMapper.oneSelect(vo);
	}

	@Override
	public List<CourseVO> regionList(CourseSearchVO svo) {
		// 지역별 조회
		return courseMapper.regionList(svo);
	}
}
