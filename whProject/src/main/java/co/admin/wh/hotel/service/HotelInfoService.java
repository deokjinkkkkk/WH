package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.hotel.vo.HotelVO;

public interface HotelInfoService {
    void insertInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException;

    List<HotelVO> hotelList();
}

