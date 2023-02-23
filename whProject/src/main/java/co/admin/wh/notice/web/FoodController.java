package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.service.FoodService;
import co.admin.wh.notice.vo.FoodVO;


@Controller
public class FoodController {
	
	@Autowired FoodService foodService;
	@Autowired 
	private FoodMapper foodMapper;
	
	//게시글 리스트 처리
	@RequestMapping("/food")
	public String foodList(Model model) {
		model.addAttribute("foodList", foodMapper.getFoodList()); 
		return "notice/foodList";
	}
	
	//글작성
	@RequestMapping("/foodForm")
	public String foodForm(Model model) {
		return "notice/foodForm";
	}
	
	
	@RequestMapping("/foodJoin.do")
	public String foodJoin(FoodVO vo, Model model) {
		model.addAttribute("foodList", foodMapper.getFoodList());
		foodService.foodInsert(vo);
		return "redirect:food";
	}
	
	//게시물 상세보기
	@RequestMapping("/foodDetail")
	public String foodDetail(Model model) {
		model.addAttribute("foodList",foodMapper.getFoodList());
		return "notice/foodDetail";
	}

	
}
