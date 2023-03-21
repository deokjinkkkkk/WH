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

import co.admin.wh.hotel.mapper.HotelMapper;
import co.admin.wh.hotel.service.HotelCrawler;
import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.CancelVO;
import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.ReservationVO;
import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.Paging;

/*
 * 작성자 : 최유림
 * 작성일자 : 2023/03/20 
 * 작성내용 : 연박 예약
 */


@Controller
public class HotelController {
	
	@Autowired
	private HotelInfoService hotelInfoService;
	
	@Autowired
	private MemberMapper memberMapper;
	
	/**
	 * 
	 * @param model
	 * @param vo
	 * @param paging
	 * @return
	 */
	@GetMapping("/hotel") // 숙소 첫 페이지
	public String hotel(Model model,
						@ModelAttribute("hvo") HotelSearchVO vo, Paging paging) {

		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));

		model.addAttribute("hotelList",hotelInfoService.hotelList(vo));
		return "hotel/hotelMain";
	}
	
	@RequestMapping("/hotelSearch") // 검색 결과 + 페이징
	public String hotelSearch(Model model, 
							@ModelAttribute("hvo") HotelSearchVO vo, 
							Paging paging) {

		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getSearchCountTotal(vo));
		
		model.addAttribute("hotelList",hotelInfoService.hotelSearchList(vo));
		return "hotel/hotelSearch";
	}
	
	@GetMapping("/hotelToday") // 오늘 예약 가능한 숙소
	public String hotelCommand(Model model,
								@ModelAttribute("hvo") HotelSearchVO vo,
								Paging paging) {
		
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		
		model.addAttribute("hotelList",hotelInfoService.CrawlingList(vo));
		
		return "hotel/hotelToday";
	}
	
	@GetMapping("/hotelDetail/{hotelId}") // 호텔 상세페이지
    public String hotelDetail(HotelVO vo, Model model) {
        model.addAttribute("h", hotelInfoService.detailSelect(vo));
        
        return "hotel/hotelDetail";
    }
	
	@PostMapping("/reservation") // 예약페이지
    public String reservation(@ModelAttribute("res")  ReservationVO vo, 
    						HotelVO hvo, 
    						MemberVO mvo, 
    						Model model, 
    						Principal principal, 
    						@RequestParam("price") int price) {
//		System.out.println(price);
//		System.out.println("=============================================");
		mvo.setId(principal.getName());
		model.addAttribute("m",memberMapper.memberSelect(mvo));
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
		model.addAttribute("id", sessionId);
		// reservationVO의 hotelid로 hotel정보조회
		return "hotel/myReservation";
	}
	
	@GetMapping("/cancelReservation") // 취소내역 조회 (이거 cancelVO..)
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
	public String cancel(@RequestBody CancelVO vo, Model model) {
		vo.setResState(0); // 현재 예약상태를 vo에 set. 0:예약완료상태
		hotelInfoService.hotelCancel(vo); // 예약완료상태 -> 환불신청상태
		hotelInfoService.insertCancelInfo(vo); // 취소 테이블에 환불정보 insert
		return "y";
	}
	
	@PostMapping("/updateInfo") // 예약자 정보수정
	public String updateInfo(ReservationVO vo, Model model) {
		hotelInfoService.ReservUpdate(vo);
		return "redirect:/myReservation";
	}
	
	@RequestMapping("/ajax/autocomplete") // 호텔검색 자동완성
	@ResponseBody
	public Map<String, Object> autocomplete(@RequestParam Map<String, Object> paramMap) throws Exception{
		List<Map<String, Object>> resultList = hotelInfoService.autocomplete(paramMap);
		paramMap.put("resultList", resultList);
		return paramMap;
	}
	
	@GetMapping("/priceList") // 낮은가격순 정렬
	public String priceList(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.priceList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/priceList";
	}
	
	@GetMapping("/priceListDesc") // 높은가격순 정렬
	public String priceListDesc(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.priceListDesc(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/priceListDesc";
	}
	
	@GetMapping("/starRatingList") // 별점순 정렬
	public String starRatingList(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.starRatingList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/starRatingList";
	}
	
	@GetMapping("/goodRatingList") // 좋아요순 정렬
	public String goodRatingList(Model model, HotelSearchVO vo,Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		List<HotelVO> hotelList = hotelInfoService.goodRatingList(vo);
		model.addAttribute("hotelList", hotelList);
		return "hotel/goodRatingList";
	}
	
//	@PostMapping("/hotelNameSearchList") // 호텔이름 검색
//	public String hotelNameSearch(Model model, HotelSearchVO vo,Paging paging) {
//		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
//		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
//
//		vo.setFirst(paging.getFirst());
//		vo.setLast(paging.getLast());
//		
//		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
//		
//		List<HotelVO> hotelList = hotelInfoService.hotelList(vo);
//		model.addAttribute("hotelList", hotelList);
//		return "hotel/hotelMain";
//	}
	
	@GetMapping("/Admin/reservation") // 관리자 예약내역 전체조회
	public String adminReservList(Model model){
		model.addAttribute("hotelList", hotelInfoService.adminReservList());
		return "hotel/adminReservList";
	}
	
	@PostMapping("/Admin/cancel") // 관리자 취소 승인
	@ResponseBody
	public String adminCancel(@RequestBody CancelVO vo, Model model) {
		vo.setResState(1); // 현재예약상태 1 : 취소신청
		hotelInfoService.hotelCancel(vo);
		hotelInfoService.updateCancelInfo(vo); // 취소테이블에 최종환불금액, 환불일자 update
		return "y";
	}
	
	@PostMapping("/Admin/updateReservInfo") // 관리자 예약정보 수정
	public String adminUpdateReservInfo(ReservationVO vo, Model model) {
		hotelInfoService.ReservUpdate(vo);
		return "redirect:/Admin/reservation";
	}
	
	@PostMapping("/adminReservSearch") // 관리자 예약검색
	public String adminReservSearch(CancelVO vo, Model model) {
	    model.addAttribute("hotelList", hotelInfoService.adminSearch(vo));
	    return "hotel/ajaxAdminReservList";
	}
	
	//예약 전 날짜 비교(이미 해당 방에 예약내역 있으면 false)
	@PostMapping("/compareToDate")
	@ResponseBody
	public boolean compareToDate(@RequestBody ReservationVO vo, Model model) {
		List<ReservationVO> hotel = hotelInfoService.compareToDate(vo);
		if(hotel.size() == 0) {
			return false; // true면 예약가능, false면 예약불가			
		}else {			
			return true;
		}
	}
};
