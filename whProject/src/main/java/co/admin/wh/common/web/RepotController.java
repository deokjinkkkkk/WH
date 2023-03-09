package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.ReportMapper;
import co.admin.wh.common.service.ReportService;
import co.admin.wh.common.vo.ReportVO;

@Controller
public class RepotController {
	
	@Autowired
	ReportMapper reportMapper;
	
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
	public String reportCompanion(Model model, ReportVO rvo) {
		model.addAttribute("rvo", reportMapper.reportCompanion(rvo));
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
}
