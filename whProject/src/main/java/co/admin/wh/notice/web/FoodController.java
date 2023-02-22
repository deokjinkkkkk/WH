package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.notice.mapper.FoodMapper;

@Controller
public class FoodController {
	
	@Autowired FoodMapper foodMapper;
	
	@RequestMapping("/food")
	public String foodForm() {
		return "notice/foodList";
	}
}
