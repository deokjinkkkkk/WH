package co.admin.wh.common.web;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.notice.vo.Paging;
import co.admin.wh.trip.mapper.CourseMapper;
import co.admin.wh.trip.mapper.TripMapper;

@Controller
public class SearchController {
	@Autowired
	TripMapper tripMapper;

	@Autowired
	CourseMapper courseMapper;

	@Autowired
	private HotelInfoService hotelInfoService;

	@RequestMapping
	public String hotelSearch(Model model, @ModelAttribute("hvo") HotelSearchVO vo, Paging paging,
			@RequestParam("checkIn") Date checkIn, @RequestParam("hotelRegion") String hotelRegion,
			@RequestParam("humanCount") String humanCount, @RequestParam("checkOut") Date checkOut) {

		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());

		paging.setTotalRecord(hotelInfoService.getCountTotal(vo));
		paging.setCheckIn(checkIn);
		paging.setHotelRegion(hotelRegion);
		paging.setHumanCount(humanCount); // model에 직접 담지 않아도 담김.
		paging.setCheckOut(checkOut);
		model.addAttribute("hotelList", hotelInfoService.hotelSearchList(vo));
		return "hotel/hotelSearch";
	}
}
