package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	


	@RequestMapping(value="/noticeDetail/{noticeCode}",method=RequestMethod.GET)
	public String noticeDe(@PathVariable("noticeCode") int noticeCode,   NoticeVO vo, Model model ) {
		vo.setNoticeCode(noticeCode);
		noticeService.noticeHit(noticeCode); //조회수 
		model.addAttribute("n", noticeService.noticendetil(vo) );
		return "notice/noticeDetail";

	}
	
	@RequestMapping("/noticeUpdateForm")
	public String noticeUpdateForm(NoticeVO vo, Model model) {
		model.addAttribute("n", noticeService.noticendetil(vo));
		return "notice/noticeUpdate";
	}
	
	
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(NoticeVO vo) {
	    noticeService.noticeUpdate(vo); // 수정된 공지사항 정보를 DB에 반영
	    return "redirect:/noticeDetail/" + vo.getNoticeCode(); // 수정된 공지사항의 상세 페이지로 이동
	}
	
	
	@PostMapping("/noticeDelete")
	public String noticeDelete(NoticeVO vo, RedirectAttributes redirectAttributes) {
	    int n = noticeService.noticeDelete(vo);
	    if (n != 0) {
	        redirectAttributes.addFlashAttribute("message", "정상적으로 삭제 되었습니다.");
	    } else {
	        redirectAttributes.addFlashAttribute("message", "삭제가 정상적으로 처리되지 않았습니다.");
	    }
	    return "redirect:/notice";
	}

}
