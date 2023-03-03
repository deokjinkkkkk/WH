package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;

public interface CourseService {
	
	List<CourseVO> courseList(CourseSearchVO cvo); // 페이징
	
	// api db에 저장
	void insertInfo(CourseVO courseVO) throws IOException, ParserConfigurationException, SAXException;
	
	
}
