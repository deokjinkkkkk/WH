package co.admin.wh.hotel.mapper;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;

@Repository
public interface HotelMapper {
    void insertHotelInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException; // 크롤링한 정보 인서트
    List<HotelVO> hotelList(); // 추천 호텔 리스트 출력
    List<HotelVO> CrawlingList(); // 오늘 예약 가능 리스트 출력
    HotelVO detailSelect(HotelVO vo); // 호텔 상세페이지
    
    String insertReservInfo(ReservationVO vo); // 호텔예약정보 인서트
    ReservationVO readReservInfo(ReservationVO vo); // 예약정보 출력
}
