package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.notice.mapper.NoticeMapper;
import co.admin.wh.notice.service.NoticeService;
import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.NoticeVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class NoticeController {
	@Autowired NoticeService noticeService;
	
	@Autowired NoticeMapper noticeMapper;
	
	@RequestMapping("/notice")
	public String notice(Model model, @ModelAttribute("svo")NoticeSearchVO svo,  Paging paging) {
		paging.setPageUnit(5);//한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); //한 페이지에 보여질 페이지 갯수
		
		paging.setTotalRecord(noticeService.getCountTotal(svo));
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		model.addAttribute("noticelists", noticeService.getNoticeList(svo)); 
		return "notice/noticelist";
	}
	
	@RequestMapping("/noticeForm")
	public String noticeForm(Model model) {
		return "notice/noticeForm";
	}
	
	@RequestMapping("/noticeInsert.do")
	public String noticeInsert(NoticeVO vo, Model model, NoticeSearchVO svo ) {
		model.addAttribute("noticelists", noticeService.getNoticeList(svo));
		noticeMapper.noticeInsert(vo);
		return "redirect:notice";
	}
	



	@GetMapping("/noticeDetail/{noticeCode}")
	public String noticeDe(NoticeVO vo, Model model) {
		model.addAttribute("noticelists", noticeService.noticendetil(vo));
		return "notice/noticeDetail";

	}
	
	
	
}
