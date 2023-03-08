package co.admin.wh.hotel.web;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.service.HotelCrawler;
import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;
import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;

@Controller
public class HotelController {
	
	@Autowired
	private HotelInfoService hotelInfoService;
	
	@Autowired
	private MemberMapper memberMapper;
	
//	@Autowired
//	public HotelController(HotelInfoService InfoService) {
//		this.hotelInfoService = InfoService;
//	}
	
	@GetMapping("/hotel") // 숙소 첫 페이지(좋아요 순 정렬)
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
	
	@GetMapping("/hotelDetail/{hotelId}") // 호텔 상세페이지
    public String hotelDetail(HotelVO vo, Model model) {
        model.addAttribute("h", hotelInfoService.detailSelect(vo));
        return "hotel/hotelDetail";
    }
	
	@PostMapping("/reservation") // 예약페이지
    public String reservation(ReservationVO vo, HotelVO hvo, MemberVO mvo, Model model, Principal principal) {
		mvo.setId(principal.getName());
		model.addAttribute("m",memberMapper.memberSelect(mvo));
		model.addAttribute("res",vo);
		model.addAttribute("h",hotelInfoService.detailSelect(hvo));
		return "hotel/reservation";
    }
	
	@PostMapping("/resIng") // 예약정보 인서트
	@ResponseBody
	public String resIng(@RequestBody ReservationVO vo, Principal principal) {
		System.out.println("넘어온 체크인날짜 ====="+vo.getCheckIn()); // 2023-03-06(지정날짜보다 하루 적게 들어옴) => 매퍼에서 하루 더 추가
		//id를 가지고 나머지 member값 가져오기
		vo.setId(principal.getName()); // 로그인한 id값을 예약vo에 set
		hotelInfoService.insertReservInfo(vo);
		return "y";
	}
	
	@GetMapping("/myReservation") // 예약내역 조회
	public String myReservation(ReservationVO vo, Principal principal, Model model) {
		String sessionId = principal.getName(); // 로그인한 id값
		model.addAttribute("res",hotelInfoService.readReservInfo(sessionId));
		// reservationVO의 hotelid로 hotel정보조회
		return "hotel/myReservation";
	}
};
