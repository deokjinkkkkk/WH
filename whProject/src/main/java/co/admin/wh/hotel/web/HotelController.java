package co.admin.wh.hotel.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.service.HotelCrawler;
import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;

@Controller
public class HotelController {
	
	private HotelInfoService hotelInfoService;
	
	@Autowired
    public HotelController(HotelInfoService InfoService) {
        this.hotelInfoService = InfoService;
    }
	
	@GetMapping("/hotel") // 숙소 첫 페이지(별점순 정렬)
	public String hotel(Model model) {
		model.addAttribute("hotelList",hotelInfoService.hotelList());
		return "hotel/hotelMain";
	}
	
	@GetMapping("/hotelToday") // 오늘 예약 가능한 숙소
	public String hotelCommand(Model model) {
		
		System.out.println("파싱되나용!!");
		
		//날짜 구하기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //원하는 데이터 포맷 지정
		
		//오늘 날짜
		String today = simpleDateFormat.format(cal.getTime()); //지정한 포맷으로 변환 
		
		//내일 날짜
		cal.add(cal.DATE, +1); //날짜를 하루 더한다.
		String tomorrow = simpleDateFormat.format(cal.getTime());
		
		//오늘 예약 가능한 숙소 크롤링
		HotelCrawler crawler = new HotelCrawler();
		String url = "https://hotels.naver.com/list?placeFileName=place%3ASeoul&adultCnt=2&checkIn="+today+"&checkOut="+tomorrow+"&includeTax=false&sortField=popularityKR&sortDirection=descending&pageIndex=1";
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
		
		model.addAttribute("hotelList",hotelInfoService.CrawlingList());
		
		System.out.println("파싱 정보 입력 완!");
		
		return "hotel/hotelToday";
	}
	
	@GetMapping("/hotelDetail/{hotelId}")
    public String hotelDetail(HotelVO vo, Model model) {
        model.addAttribute("h", hotelInfoService.detailSelect(vo));
        return "hotel/hotelDetail";
    }
	
	@PostMapping("/reservation")
    public String reservation(ReservationVO vo, HotelVO hvo, Model model) {
		model.addAttribute("res",vo);
		model.addAttribute("h",hotelInfoService.detailSelect(hvo));
		return "hotel/reservation";
    }
	
	@GetMapping("/resIng")
	public String resIng(ReservationVO vo, Model model) {
		String result = hotelInfoService.insertReservInfo(vo);
		if(result != null) {
			return "y";			
		}else {
			return "n";
		}
	}
	
	@GetMapping("/resComplete")
	public String resComplete() {
		return "hotel/resComplete";
	}
};
