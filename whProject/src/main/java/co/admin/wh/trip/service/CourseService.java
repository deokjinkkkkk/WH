package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;

public interface CourseService {
		
	List<CourseVO> courseList(); // 전체조회(페이징)
	
	// api db에 저장
	void insertInfo(CourseVO courseVO) throws IOException, ParserConfigurationException, SAXException;
	
	int getCountTotal(CourseSearchVO svo); // 페이지수 관리
	
	CourseVO detailSelect(CourseVO vo); //상세보기 페이지
	
}
