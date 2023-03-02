package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.RoomVO;

public interface HotelInfoService {
    void insertHotelInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException; // 크롤링한 정보 인서트
    void insertRoomInfo(RoomVO roomVO);
    List<HotelVO> hotelList(); // 추천 호텔 리스트 출력
    List<HotelVO> CrawlingList(); // 오늘 예약 가능 리스트 출력
}

