package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

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
	public List<CourseVO> courseList(CourseSearchVO cvo) {
		// 페이징
		return courseMapper.courseList(cvo);
	}

	@Override
	public void insertInfo(CourseVO courseVO) {
		// api DB 등록
		courseMapper.insertInfo(courseVO);
		
	}
}
