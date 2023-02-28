package co.admin.wh.trip.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.TripVO;

public interface TripService {
	
	void insertInfo(TripVO tripVO) throws IOException, ParserConfigurationException, SAXException;
	List<TripVO> tripList();
}
