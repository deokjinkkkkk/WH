package co.admin.wh.notice.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping("/noticeDetail")
	public String noticeDetail(Model model) {
		model.addAttribute("companionList", noticeService.getNoticeList()); 
		return "notice/noticeDetail";
	}
	
}
