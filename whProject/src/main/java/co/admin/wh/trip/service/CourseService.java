package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.TripVO;

public interface CourseService {
		
	List<CourseVO> courseList(CourseSearchVO svo); // 전체조회(페이징)
	
	// api db에 저장
	void insertInfo(CourseVO courseVO) throws IOException, ParserConfigurationException, SAXException;
	
	int getCountTotal(CourseSearchVO svo); // 페이지수 관리
	
	List<CourseVO> detailSelect(CourseVO vo); // 상세보기 페이지
	
	CourseVO oneSelect(CourseVO vo);// 코스 단건조회
}
