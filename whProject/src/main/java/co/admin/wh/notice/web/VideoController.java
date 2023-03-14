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
		
		model.addAttribute("video", videoService.videoSelect(vo));
		return "notice/video";
	}
	
	@RequestMapping("/seoul")
	public String seoul(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.seoul(svo);
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
	@RequestMapping("/incheon")
	public String incheon(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.incheon(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/gwangju")
	public String gwangju(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.gwangju(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/daejeon")
	public String daejeon(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.daejeon(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/ulsan")
	public String ulsan(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.ulsan(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/sejong")
	public String sejong(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.sejong(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/gyeonggi")
	public String gyeonggi(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.gyeonggi(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/gangwon")
	public String gangwon(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.gangwon(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/chungbuk")
	public String chungbuk(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.chungbuk(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/chungnam")
	public String chungnam(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.chungnam(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/jeonbuk")
	public String jeonbuk(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.jeonbuk(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/jeonnam")
	public String jeonnam(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.jeonnam(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/kyungbuk")
	public String kyungbuk(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.kyungbuk(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/kyungnam")
	public String kyungnam(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.kyungnam(svo);
		model.addAttribute("videoSelect", videoSelect);
		
		return "notice/sortingVideo";
	}
	@RequestMapping("/jeju")
	public String jeju(Model model,VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(videoService.videoTotal(svo));
		
		List<VideoVO> videoSelect = videoService.jeju(svo);
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
