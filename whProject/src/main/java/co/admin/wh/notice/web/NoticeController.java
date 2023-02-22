package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.notice.mapper.NoticeMapper;
import co.admin.wh.notice.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	@Autowired NoticeMapper noticeMapper;
	
	@RequestMapping("/notice")
	public String noticeForm(Model model) {
		model.addAttribute("noticelist", noticeService.getNoticeList()); 
		return "notice/noticelist";
	}
	
	
	
}
