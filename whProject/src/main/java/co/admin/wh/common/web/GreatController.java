package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.GreatMapper;
@Controller
public class GreatController {
	@Autowired GreatMapper mapper;
	
	@RequestMapping("/great") //마이페이지에서 좋아요리스트 창으로 이동하기 만들기 
	public String greatForm(Model model) {
		model.addAttribute("g", mapper.myGreatList());
		return "comments/greatForm";
	}
}
