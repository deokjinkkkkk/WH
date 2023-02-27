package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.model.IModel;

import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.service.FoodService;
import co.admin.wh.notice.vo.FoodImgVO;
import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;
import co.admin.wh.notice.vo.Paging;
import lombok.extern.log4j.Log4j2;


@Controller
public class FoodController {
	
	@Autowired FoodMapper foodMapper;
	@Autowired FoodService foodService;
	
	
	//게시글 리스트 처리
	@RequestMapping("/food")
	public String foodList(Model model, @ModelAttribute("fsvo")FoodSearchVO svo, Paging paging ) {
		paging.setPageUnit(5);//한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); //한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(foodMapper.getCountTotal(svo));
		model.addAttribute("foodList", foodMapper.getFoodList(svo)); 
		
		return "notice/foodList";
	}
	
	//글작성
	@RequestMapping("/foodForm")
	public String foodForm(Model model) {
		return "notice/foodForm";
	}
	
	
	@RequestMapping("/foodJoin.do")
	public String foodJoin(FoodVO vo, Model model, FoodImgVO ivo) {
		model.addAttribute("foodList", foodMapper.getFoodList(vo));
		foodService.foodInsert(vo);
		foodService.imgInsert(ivo);
		return "redirect:food";
	}
	
	//게시물 상세보기
//	@GetMapping("/fooDetail/{foodCode}")
//	public String foodDetail(FoodVO vo,Model model) {
//		model.addAttribute("foodList",foodMapper.getFoodList(vo));
//		return "notice/foodDetail";
//	}
	
	@RequestMapping(value="/foodDetail/{foodCode}", method=RequestMethod.GET)
	public String foodDetail(@PathVariable("foodCode") int foodCode, FoodVO vo, Model model) {
		vo.setFoodCode(foodCode);

		model.addAttribute("food",foodService.detailSelect(vo));
		return "notice/foodDetail";
		
	}

	
}
