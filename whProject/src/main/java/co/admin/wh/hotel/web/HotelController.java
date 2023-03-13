package co.admin.wh.hotel.web;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.service.HotelCrawler;
import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;
import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class HotelController {
	
	@Autowired
	private HotelInfoService hotelInfoService;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@GetMapping("/hotel") // 숙소 첫 페이지
	public String hotel(Model model, @ModelAttribute("hvo") HotelSearchVO vo, Paging paging) {

		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));

		model.addAttribute("hotelList",hotelInfoService.hotelList(vo));
		return "hotel/hotelMain";
	}
	
	@RequestMapping("/hotelSearch") // 검색 결과 + 페이징
	public String hotelSearch(Model model, @ModelAttribute("hvo") HotelSearchVO vo, Paging paging, 
							@RequestParam("checkIn") Date checkIn, @RequestParam("hotelRegion") String hotelRegion, @RequestParam("humanCount") String humanCount) {

		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));

//		paging.setSearchInfo(checkIn, hotelRegion, humanCount); // 검색정보
		
//		model.addAttribute("paging", paging);
		
		paging.setCheckIn(checkIn);
		paging.setHotelRegion(hotelRegion);
		paging.setHumanCount(humanCount); // model에 직접 담지 않아도 담김.
		model.addAttribute("hotelList",hotelInfoService.hotelSearchList(vo));
//		model.addAttribute("checkIn", checkIn);
//		model.addAttribute("hotelRegion", hotelRegion);
//		model.addAttribute("humanCount", humanCount);
		return "hotel/hotelSearch";
	}
	
	@GetMapping("/hotelToday") // 오늘 예약 가능한 숙소
	public String hotelCommand(Model model, @ModelAttribute("hvo") HotelSearchVO vo, Paging paging) {
		
		System.out.println("파싱되나용!!");
		
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
		
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		
		model.addAttribute("hotelList",hotelInfoService.CrawlingList(vo));
		
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
		int hotelId = vo.getHotelId();
		hotelInfoService.minusRoomCount(hotelId); // hotel테이블의 방 개수 -1
		return "y";
	}
	
	@GetMapping("/myReservation") // 예약내역 조회
	public String myReservation(ReservationVO vo, Principal principal, Model model) {
		String sessionId = principal.getName(); // 로그인한 id값
		model.addAttribute("res",hotelInfoService.readReservInfo(sessionId));
		model.addAttribute("id", sessionId);
		// reservationVO의 hotelid로 hotel정보조회
		return "hotel/myReservation";
	}
	
	@GetMapping("/cancelReservation") // 취소내역 조회
	public String cancelReservation(ReservationVO vo, Principal principal, Model model) {
		String sessionId = principal.getName(); // 로그인한 id값
		model.addAttribute("res",hotelInfoService.readCancelReservInfo(sessionId));
		model.addAttribute("id", sessionId);
		// reservationVO의 hotelid로 hotel정보조회
		return "hotel/cancelReservation";
	}
	
	@GetMapping("/finReservation") // 지난예약내역 조회
	public String finReservation(ReservationVO vo, Principal principal, Model model) {
		String sessionId = principal.getName(); // 로그인한 id값
		model.addAttribute("res",hotelInfoService.readFinReservInfo(sessionId));
		model.addAttribute("id", sessionId);
		// reservationVO의 hotelid로 hotel정보조회
		return "hotel/finReservation";
	}
	
	@PostMapping("/cancel") // 예약취소
	@ResponseBody
	public String cancel(@RequestBody ReservationVO vo, Model model) {
		hotelInfoService.hotelCancel(vo);
		return "y";
	}
	
	@PostMapping("/updateInfo") // 예약자 정보수정
	@ResponseBody
	public String updateInfo(@RequestBody ReservationVO vo, Model model) {
		hotelInfoService.ReservUpdate(vo);
		return "y";
	}
	
	@RequestMapping("/ajax/autocomplete") // 호텔검색 자동완성
	@ResponseBody
	public Map<String, Object> autocomplete(@RequestParam Map<String, Object> paramMap) throws Exception{
		List<Map<String, Object>> resultList = hotelInfoService.autocomplete(paramMap);
		paramMap.put("resultList", resultList);
		return paramMap;
	}
	
	@PostMapping("/priceList") // 낮은가격순 정렬
	public String priceList(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.priceList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/sortingHotelList";
	}
	
	@PostMapping("/priceListDesc") // 높은가격순 정렬
	public String priceListDesc(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.priceListDesc(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/sortingHotelList";
	}
	
	@PostMapping("/starRatingList") // 별점순 정렬
	public String starRatingList(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.starRatingList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/sortingHotelList";
	}
	
	@PostMapping("/goodRatingList") // 좋아요순 정렬
	public String goodRatingList(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.goodRatingList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/sortingHotelList";
	}
	
	@PostMapping("/hotelNameSearchList") // 호텔이름 검색
	public String hotelNameSearch(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		
		List<HotelVO> hotelList = hotelInfoService.hotelNameSearchList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/hotelNameSearchList";
	}
};
