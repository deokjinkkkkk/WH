package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;

public interface HotelInfoService {
    void insertHotelInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException; // 크롤링한 정보 인서트
    List<HotelVO> hotelList(HotelSearchVO vo); // 추천 호텔 리스트 출력
    List<HotelVO> CrawlingList(HotelSearchVO vo); // 오늘 예약 가능 리스트 출력
    int getCountTotal(HotelSearchVO vo); // 페이지 수 관리
    HotelVO detailSelect(HotelVO vo); // 호텔 상세페이지
    void insertReservInfo(ReservationVO vo); // 호텔예약정보 인서트
    List<ReservationVO> readReservInfo(String sessionId); // 예약정보 출력
    List<ReservationVO> readCancelReservInfo(String sessionId); // 취소내역 출력
    void hotelCancel(ReservationVO vo); // 예약 취소(업데이트문)
    void ReservUpdate(ReservationVO vo); // 예약자 정보수정(업데이트문)
}

