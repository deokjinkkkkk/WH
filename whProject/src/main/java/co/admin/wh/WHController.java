package co.admin.wh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WHController {

	@GetMapping("/home")
	public String home(Model model) {
		return "content/home";
	}
	
	@GetMapping("/main")
	public String main(Model model) {
		return "content/main";
	}
	
	@GetMapping("/hotel")
	public String hotel(Model model) {
		return "content/hotelMain";
	}
	
	@GetMapping("/companion")
	public String companion(Model model) {
		return "notice/companion";
	}
	
	@GetMapping("/companionForm")
	public String companionForm(Model model) {
		return "notice/companionForm";
	}
}
