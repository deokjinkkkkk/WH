package co.admin.wh.notice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.service.CommonService;
import co.admin.wh.common.vo.CommonVO;
import co.admin.wh.notice.mapper.VideoMapper;
import co.admin.wh.notice.service.VideoService;
import co.admin.wh.notice.vo.CompanionVO;
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
		
		model.addAttribute("video", videoService.videoSelect(svo));
		return "notice/video";
	}
	
	@RequestMapping("/videoSelect")
	public String videoSelect(Model model,VideoVO vo, @ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		model.addAttribute("video", videoService.region(svo));
		return "notice/videoSelect";
	}
	
	@RequestMapping("/region")
	public String seoul(Model model,@ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.region(svo);
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
	public String videoDatail(Model model, VideoVO vo,@PathVariable("videoCode") int videoCode) {
		vo.setVideoCode(videoCode);
		videoService.videoHit(videoCode);
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoDetail";
	}
	
	@RequestMapping("/videoSelectDetail/{videoCode}")
	public String videoSelectDetail(Model model, VideoVO vo,@PathVariable("videoCode") int videoCode) {
		vo.setVideoCode(videoCode);
		videoService.videoHit(videoCode);
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoSelectDetail";
	}
	
	
	@RequestMapping("/videoUpdateForm")
	public String companionUpdateForm(Model model, VideoVO vo ) {
		model.addAttribute("co", commomService.commonLocal());
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoUpdateForm";
	}
	
	@RequestMapping("/videoUpdate")
	public String companionUpdate(Model model, VideoVO vo ) {
		model.addAttribute("c", videoService.videoDetail(vo));
		model.addAttribute("d", videoService.videoUpdate(vo));
		return "redirect:video";
	}
	
	
}
