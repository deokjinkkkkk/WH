package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.ReportMapper;
import co.admin.wh.common.service.CommonService;
import co.admin.wh.common.service.ReportService;
import co.admin.wh.common.service.SanctionsService;
import co.admin.wh.common.vo.CommonVO;
import co.admin.wh.common.vo.ReportVO;
import co.admin.wh.common.vo.SanctionsVO;

@Controller
public class RepotController {
	
	@Autowired
	ReportMapper reportMapper;
	
	@Autowired
	ReportService reportSevice;
	
	@Autowired
	CommonService commomService;
	
	@Autowired
	SanctionsService sanctionsService;
	
	@RequestMapping("/report")
	public String reportInsert(ReportVO rvo) {
		System.out.println(rvo);
		reportMapper.reportInsert(rvo);
		return "redirect:companion";
	}
	
	@RequestMapping("/reportList")
	public String reportList() {

		return "common/reportList";
	}
	
	@RequestMapping("/reportCompanion")
	public String reportCompanion(Model model, ReportVO rvo, CommonVO cvo) {
		rvo.setRepCode(rvo.getRepCode());
//		model.addAttribute("rv", reportMapper.reportCode(rvo));
		model.addAttribute("rvo", reportSevice.reportCompanion(rvo));
		model.addAttribute("cs", commomService.commonState());
		return "common/reportCompanion";
	}
	
	@RequestMapping("/reportFree")
	public String reportFree(Model model, ReportVO rvo) {
		model.addAttribute("rvo", reportMapper.reportFree(rvo));
		return "common/reportFree";
	}
	
	@RequestMapping("/reportReview")
	public String reportReview(Model model, ReportVO rvo) {
		model.addAttribute("rvo", reportMapper.reportReview(rvo));
		return "common/reportReview";
	}
	
	@RequestMapping("/reportComment")
	public String reportComment(Model model, ReportVO rvo) {
		model.addAttribute("rvo", reportMapper.reportComment(rvo));
		return "common/reportComment";
	}
	
	@PostMapping("/duration")
	public String duration(ReportVO vo, SanctionsVO svo) {
		reportMapper.durationUpdate(vo);
		sanctionsService.sanctionsInsert(svo);
		return "redirect:reportCompanion";
	}
}
