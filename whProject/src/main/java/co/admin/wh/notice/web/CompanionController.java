package co.admin.wh.notice.web;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
=======

>>>>>>> branch 'jin' of https://github.com/deokjinkkkkk/WH.git
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.service.CompanionService;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class CompanionController {
	@Autowired
	private CompanionMapper companionMapper;
	
	@Autowired
	CompanionService companionService;
	
	@RequestMapping("/companion")
	public String companion(Model model ,@ModelAttribute("fcvo")CompanionSearchVO cvo ,Paging paging) {
		paging.setPageUnit(2);
		paging.setPageSize(5);
		
		cvo.setFirst(paging.getFirst());
		cvo.setLast(paging.getLast());
		
		paging.setTotalRecord(companionMapper.getCountTotal(cvo));
		
		model.addAttribute("companionList", companionService.getCompanionList(cvo)); 
		return "notice/companion";
	}

	@RequestMapping("/companionForm")
	public String companionForm(Model model) {
		return "notice/companionForm";
	}

	@RequestMapping("/companionJoin.do")
	public String companionJoin(CompanionVO vo, Model model, ImageVO ivo) {
		model.addAttribute("companionList", companionService.getCompanionList(null));
		companionMapper.companionInsert(vo);
		companionMapper.imgInsert(ivo);
		return "redirect:companion";
	}
	
	@RequestMapping("/companionDetail/{compCode}")
	public String companionDetail(Model model, CompanionVO compCode) {
		model.addAttribute("detailSelect", companionService.detailSelect(compCode)); 
		//model.addAttribute("companionList", companionService.getCompanionList());
		return "notice/companionDetail";
	}
	

	 
}
