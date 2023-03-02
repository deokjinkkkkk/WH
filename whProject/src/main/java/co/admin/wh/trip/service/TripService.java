package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

public interface TripService {
	
	List<TripVO> tripList(TripSearchVO svo); //페이징

	// api DB 저장 
	void insertInfo(TripVO tripVO) throws IOException, ParserConfigurationException, SAXException;
	
	int getCountTotla(TripSearchVO svo); // 페이지수 관리

	TripVO detailSelect(TripVO tvo); //상세보기 페이지
}
