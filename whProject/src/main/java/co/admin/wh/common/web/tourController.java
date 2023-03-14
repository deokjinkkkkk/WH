package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.service.TourSerivce;
import co.admin.wh.common.vo.tourVO;

@Controller
public class tourController {
	@Autowired
	TourSerivce tourService;
	
	@RequestMapping("/tourReForm")
	public String tourReform(tourVO vo) {
		
		return "tour/tourRequestForm";
	}
	@PostMapping("/tourInsert")
	public String tourInsert(tourVO vo) {
		tourService.tourInsert(vo);
		return "content/main";
	}
}
