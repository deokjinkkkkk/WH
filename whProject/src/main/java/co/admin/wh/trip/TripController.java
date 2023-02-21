package co.admin.wh.trip;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TripController {
	
	@RequestMapping("/tripSearch")
	public String tripSearch(Model model) {
		return "trip/tripSearch";
	}
	
	@RequestMapping("/tripDetail")
	public String tripDetail(Model model) {
		return "trip/tripDetail";
	}
	
	@RequestMapping("/tripCourse")
	public String tripCourse(Model model) {
		return "trip/tripCourse";
	}
}
