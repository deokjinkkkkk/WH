package co.admin.wh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.trip.service.TripService;
import co.admin.wh.trip.vo.TripVO;

@Controller
public class WHController {

	@Autowired
	HotelInfoService hotelInfoService;
	
	@Autowired
	TripService tripService;	
	
	@GetMapping("/main")
	public String main(Model model,TripVO vo, HotelSearchVO svo) {
			model.addAttribute("t", tripService.mainTripList(vo));
			model.addAttribute("h", hotelInfoService.mainList(svo));
		return "content/main";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin/indexAdmin";
	}

}
