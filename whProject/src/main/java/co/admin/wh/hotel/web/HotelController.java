package co.admin.wh.hotel.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {

	@GetMapping("/hotel")
	public String hotel(Model model) {
		return "content/hotelMain";
	}
}
