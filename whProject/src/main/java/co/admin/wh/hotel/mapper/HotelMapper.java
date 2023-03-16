package co.admin.wh.hotel.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;

@Repository
public interface HotelMapper {
	
	//메인페이지
    List<HotelVO> mainList(HotelSearchVO vo);
    
	//호텔 리스트 + 페이징
    void insertHotelInfo(HotelVO hotelVO) throws IOException, ParserConfigurationException, SAXException; // 크롤링한 정보 인서트
    List<HotelVO> hotelList(HotelSearchVO vo); // 추천 호텔 리스트 출력
    List<HotelVO> CrawlingList(HotelSearchVO vo); // 오늘 예약 가능 리스트 출력
    HotelVO detailSelect(HotelVO vo); // 호텔 상세페이지
    int getCountTotal(HotelSearchVO vo); // 페이지 수 관리
    
    //정렬
    List<HotelVO> priceList(HotelSearchVO vo); // 낮은가격순 정렬
    List<HotelVO> priceListDesc(HotelSearchVO vo); // 높은가격순 정렬
    List<HotelVO> starRatingList(HotelSearchVO vo); // 별점순 정렬
    List<HotelVO> goodRatingList(HotelSearchVO vo); // 좋아요순 정렬

    //검색
    List<Map<String, Object>>autocomplete(Map<String, Object> paramMap) throws Exception; // 호텔이름 검색어 자동완성
    List<HotelVO> hotelSearchList(HotelSearchVO vo); // 날짜, 지역,인원으로 검색한 리스트 출력
    List<HotelVO> hotelNameSearchList(HotelSearchVO vo); // 호텔이름 검색 리스트 출력
    
    //예약
    void insertReservInfo(ReservationVO vo); // 호텔예약정보 인서트
    void minusRoomCount(int hotelId); // 예약 후 hotel테이블 room_count-1
    
    //마이페이지 & 관리자페이지
    List<ReservationVO> readReservInfo(String sessionId); // 예약정보 출력
    void ReservUpdate(ReservationVO vo); // 예약자 정보수정(업데이트문)
    void hotelCancel(ReservationVO vo); // 예약 취소(업데이트문)
    void insertCancelInfo(ReservationVO vo); // 회원이 예약취소신청 시 취소 테이블에 취소정보 인서트 
    List<ReservationVO> readFinReservInfo(String sessionId); // 지난예약정보 출력
    List<ReservationVO> readCancelReservInfo(String sessionId); // 취소내역 출력
    
    
    // 관리자
    List<ReservationVO> adminReservList(); // 관리자 예약내역 전체조회
    List<ReservationVO> adminSearch(String option, String content); // 관리자 예약내역 검색
    

}

