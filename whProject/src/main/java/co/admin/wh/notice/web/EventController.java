package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.EventMapper;
import co.admin.wh.notice.service.EventService;
import co.admin.wh.notice.vo.EventSearchVO;
import co.admin.wh.notice.vo.EventVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class EventController {
	
	@Autowired EventMapper eventMapper;
	@Autowired EventService eventService;
	
	//전체조회
	@RequestMapping("/event")
	public String eventList(Model model, @ModelAttribute("esvo") 
	EventSearchVO svo, Paging paging ) {
		paging.setPageUnit(5);//한페이지에 출력 할 레코드 건수
		paging.setPageSize(10); //한페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(eventMapper.getCountTotal(svo));
		model.addAttribute("eventList", eventMapper.getEventList(svo));
		return "notice/eventList";
	}
	
	//상세보기
	@RequestMapping(value="/eventDetail/{eventCode}", method=RequestMethod.GET)
	public String eventDetail(@PathVariable("eventCode") int eventCode, EventVO vo, Model model) {
		vo.setEventCode(eventCode);
		eventService.hitUpdate(eventCode); // 조회수증가
		
		model.addAttribute("event",eventService.detailSelect(vo));
		return "notice/eventDetail";
	}
	
	//글등록
	@RequestMapping("/eventInsert.do")
	public String eventJoin(EventVO vo, Model model, ImageVO ivo) {
		
		eventService.eventInsert(vo);
		eventService.imgInsert(ivo);
		
		return "redirect:event";
	}
	
	//수정
	@PostMapping("/eventUpdate")
	public String eventUpdate(@ModelAttribute("eventVO") EventVO vo, Model model) {
	model.addAttribute("event", eventService.eventUpdate(vo));
	return "redirect:/eventDetail/" + vo.getEventCode();
	}
	
	//삭제
	@PostMapping("/eventDelete")
	public String eventDelete(EventVO vo, RedirectAttributes redirectAttributes) {
		int n = eventService.eventDelete(vo);
		if (n != 0) {
			redirectAttributes.addFlashAttribute("message","정상적으로 삭제되었습니다.");
		}else {
			redirectAttributes.addFlashAttribute("message","삭제가 정상적으로 처리되지 않았습니다.");
		}
		return "redirect:/event";
	}
	
	//이미지등록

}
