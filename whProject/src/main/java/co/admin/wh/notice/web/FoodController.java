package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.service.FoodService;
import co.admin.wh.notice.vo.PagingVO;

@Controller
public class FoodController {
	
	@Autowired FoodService foodService;
	@Autowired 
	private FoodMapper foodMapper;
	
	
	@RequestMapping("/food")
	public String foodList(Model model) {
		model.addAttribute("foodList", foodMapper.getFoodList()); 
		return "notice/foodList";
	}
	
	@RequestMapping("/foodForm")
	public String foodForm(Model model) {
		return "notice/foodForm";
	}
	

	
}
