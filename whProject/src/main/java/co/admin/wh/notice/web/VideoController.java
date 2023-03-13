package co.admin.wh.notice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.service.CommonService;
import co.admin.wh.notice.mapper.VideoMapper;
import co.admin.wh.notice.vo.VideoVO;

@Controller
public class VideoController {
	
	@Autowired
	CommonService commomService;
	
	@Autowired
	VideoMapper videoMapper;
	
	@RequestMapping("/video")
	public String video() {

		return "notice/video";
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
}
