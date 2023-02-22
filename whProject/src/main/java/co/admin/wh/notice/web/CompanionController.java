package co.admin.wh.notice.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.service.CompanionService;
import co.admin.wh.notice.vo.CompanionVO;

@Controller
public class CompanionController {
	@Autowired
	private CompanionMapper companionMapper;
	@Autowired
	CompanionService companionService;
	
	
	
	@RequestMapping("/companion")
	public String companion(Model model) {
		model.addAttribute("companionList", companionService.getCompanionList()); 
		return "notice/companion";
	}

	@RequestMapping("/companionForm")
	public String companionForm(Model model) {
		return "notice/companionForm";
	}

	@RequestMapping("/companionJoin.do")
	public String companionJoin(CompanionVO vo) {
		companionMapper.companionInsert(vo);
		return "notice/companion";
	}

}
