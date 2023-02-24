package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.admin.wh.notice.mapper.NoticeMapper;
import co.admin.wh.notice.service.NoticeService;
import co.admin.wh.notice.vo.NoticeVO;

@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	@Autowired NoticeMapper noticeMapper;
	
	@RequestMapping("/notice")
	public String notice(Model model) {
		model.addAttribute("noticelists", noticeService.getNoticeList()); 
		return "notice/noticelist";
	}
	
	@RequestMapping("/noticeForm")
	public String noticeForm(Model model) {
		return "notice/noticeForm";
	}
	
	@RequestMapping("/noticeInsert.do")
	public String noticeInsert(NoticeVO vo, Model model) {
		model.addAttribute("noticelists", noticeService.getNoticeList());
		noticeMapper.noticeInsert(vo);
		return "redirect:notice";
	}
	
	@RequestMapping("/noticeDetile.do")
	public String noticeDe(NoticeVO svo, Model model) {
		model.addAttribute("noticelists", noticeService.ndetil(svo));
		return "notice/noticDetile";
	}
	
}
