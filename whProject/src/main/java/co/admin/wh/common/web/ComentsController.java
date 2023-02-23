package co.admin.wh.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComentsController {
	
	
	@RequestMapping("/comments")
	public String loginForm() {
		return "comments/commentsForm";
	}
	 
}
