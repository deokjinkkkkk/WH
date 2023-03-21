package co.admin.wh.notice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/video") //영상 게시판 목록
	public String video(Model model, VideoVO vo, @ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(6);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(5); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(videoService.videoTotal(svo));
		if (svo.getVideoRegion() == null) {
			svo.setVideoRegion("11");
		}

		model.addAttribute("video", videoService.region(svo));

		return "notice/video";
	}

	@RequestMapping("/videoSelect") //영상 게시물 목록
	public String videoSelect(Model model, VideoVO vo, @ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(6);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(5); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(videoService.videoTotal(svo));
		if (svo.getVideoRegion() == null) {
			svo.setVideoRegion("11");
		}
		model.addAttribute("video", videoService.region(svo));
		return "notice/videoSelect";
	}

	@RequestMapping("/region") //페이징관련
	public String region(Model model, @ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(6);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(5); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(videoService.videoTotal(svo));

		List<VideoVO> videoSelect = videoService.region(svo);
		model.addAttribute("videoSelect", videoSelect);

		return "notice/sortingVideo";
	}

	@RequestMapping("/regions") //페이징관련
	public String regions(Model model, @ModelAttribute("hvo") VideoSearchVO svo, Paging paging) {
		paging.setPageUnit(10);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		paging.setTotalRecord(videoService.videoTotal(svo));

		List<VideoVO> videoSelect = videoService.region(svo);
		model.addAttribute("videoSelect", videoSelect);

		return "notice/sortingVideoSelect";
	}

	@RequestMapping("/videoForm") //영상 게시판 등록 폼
	public String videoForm(Model model) {
		model.addAttribute("co", commomService.commonLocal());
		return "notice/videoForm";
	}

	@RequestMapping("/videoInsert") //영상 게시판 등록
	public String companionJoin(VideoVO vo) {
		videoMapper.videoInsert(vo);
		return "redirect:video";
	}

	@RequestMapping("/videoDetail/{videoCode}") //영상 상세 페이지
	public String videoDatail(Model model, VideoVO vo) {

		videoService.videoHit(vo.getVideoCode());
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoDetail";
	}

	@RequestMapping("/videoSelectDetail/{videoCode}")//영상 상세 페이지
	public String videoSelectDetail(Model model, VideoVO vo, @PathVariable("videoCode") int videoCode) {
		vo.setVideoCode(videoCode);
		videoService.videoHit(videoCode);
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoSelectDetail";
	}

	@RequestMapping("/videoUpdateForm") //영상 수정 폼
	public String companionUpdateForm(Model model, VideoVO vo) {
		model.addAttribute("co", commomService.commonLocal());
		model.addAttribute("d", videoService.videoDetail(vo));
		return "notice/videoUpdateForm";
	}

	@RequestMapping("/videoUpdate") //영상 수정
	public String companionUpdate(Model model, VideoVO vo) {
		videoService.videoUpdate(vo);
		return "redirect:video";
	}

	@RequestMapping("/videoDelete") //영상 삭제
	public String videoDelete(VideoVO vo) {
		videoService.videoDelete(vo);
		return "redirect:video";

	}
}
