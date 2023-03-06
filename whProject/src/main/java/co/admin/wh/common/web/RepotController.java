package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.mapper.ReportMapper;
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
}
