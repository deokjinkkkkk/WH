package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.notice.mapper.NoticeMapper;
@Controller
public class NoticeController {
	@Autowired NoticeMapper noticeMapper;
	
	@RequestMapping("/notice")
	public String noticeForm() {
		return "notice/noticelist";
	}
}
