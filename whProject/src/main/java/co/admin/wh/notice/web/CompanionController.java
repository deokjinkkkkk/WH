package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.notice.service.CompanionService;
import co.admin.wh.notice.vo.CompanionVO;

@Controller
public class CompanionController {
	@Autowired
	private CompanionService companionService;
	
	@RequestMapping("/companionJoin.do")
	public String companionJoin(CompanionVO vo) {
		companionService.companionInsert(vo);
		return "content/home";
	}
}