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

	@GetMapping("/admin")
	public String admin(Model model) {
		return "admin/indexAdmin";
	}

}
