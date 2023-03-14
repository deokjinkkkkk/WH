package co.admin.wh.notice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.service.CommonService;
import co.admin.wh.notice.mapper.VideoMapper;
import co.admin.wh.notice.service.VideoService;
import co.admin.wh.notice.vo.Paging;
import co.admin.wh.notice.vo.VideoSearchVO;
import co.admin.wh.notice.vo.VideoVO;

@Controller
public class VideoController {
	
	@Autowired
	CommonService commomService;
	
	@Autowired
	VideoMapper videoMapper;
	
	@Autowired
	VideoService videoService;
	
	@RequestMapping("/video")
	public String video(Model model,VideoVO vo, @ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		model.addAttribute("v", videoService.videoSelect(vo));
		return "notice/video";
	}
	
	
	@RequestMapping("/daegu")
	public String daegu(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.daegu(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/busan")
	public String busan(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.busan(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	
	
	
	
	
	
	@RequestMapping("/videoForm")
	public String videoForm(Model model) {
		model.addAttribute("co", commomService.commonLocal());
		return "notice/videoForm";
	}
	
	@RequestMapping("/videoInsert")
	
	public String companionJoin(VideoVO vo) {
		videoMapper.videoInsert(vo);
		return "redirect:video";
	}
	
	@RequestMapping("/videoDetail/{videoCode}")
	public String videoDatail(Model model, VideoVO vo) {
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoDetail";
	}
}
