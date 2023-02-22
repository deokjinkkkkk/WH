package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.service.FoodService;

@Controller

public class FoodController {
	
	@Autowired FoodService foodService;
	
	@Autowired FoodMapper foodMapper;
	
	@RequestMapping("/food")
	public String foodForm(Model model) {
		model.addAttribute("foodList", foodMapper.getFoodList()); 
		return "notice/foodList";
	}
}
