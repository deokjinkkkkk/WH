package co.admin.wh.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.service.HotelCrawler;
import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelVO;

@Component
public class Schedule {
	
	@Autowired HotelInfoService hotelInfoService;

	
	@Scheduled(cron = "0 0 0 * * *")
	public void jejuHotelCrawl() {
		//날짜 구하기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //원하는 데이터 포맷 지정
		
		//오늘 날짜
		String today = simpleDateFormat.format(cal.getTime()); //지정한 포맷으로 변환 
		
		//내일 날짜
		cal.add(cal.DATE, +1); //날짜를 하루 더한다.
		String tomorrow = simpleDateFormat.format(cal.getTime());
		
		//오늘 예약 가능한 숙소 크롤링(크롤링 지역 변경 여기서)
		HotelCrawler crawler = new HotelCrawler();
		String url = "https://hotels.naver.com/list?placeFileName=place%3AJeju_Province&adultCnt=2&checkIn="+today+"&checkOut="+tomorrow+"&includeTax=false&sortField=rating&sortDirection=descending";
		List<HotelVO> hotelList = crawler.hotelCrawling(url); // HotelCrawler.java
		
		//for문 돌면서 리스트에서 vo 하나씩 인서트.
		for (HotelVO hotelVO : hotelList) {
			try {
				hotelInfoService.insertHotelInfo(hotelVO);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			}
		}
	}
}
