package co.admin.wh.trip.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.trip.mapper.CourseMapper;
import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;

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
	public List<CourseVO> courseList() {
		// 페이징
		return courseMapper.courseList();
	}

	@Override
	public int getCountTotal(CourseSearchVO svo) {
		// 페이지 수 관리
		return courseMapper.getCountTotal(svo);
	}

	@Override
	public CourseVO detailSelect(CourseVO cvo) {
		// 상세페이지 보기
		return courseMapper.detailSelect(cvo);
	}
}
