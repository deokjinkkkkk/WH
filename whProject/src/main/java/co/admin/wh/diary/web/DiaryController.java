package co.admin.wh.diary.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.diary.mapper.DiaryMapper;
import co.admin.wh.diary.service.DiaryService;
import co.admin.wh.diary.vo.DiaryVO;
import co.admin.wh.member.vo.MemberVO;


@Controller
public class DiaryController {
	@Autowired
	DiaryService diaryService;
	
	@Autowired
	DiaryMapper diaryMapper;
	
	@Autowired
	ServletContext servletContext;
	
	@Value("${wh.saveimg}")
	private String saveimg;

	@Autowired
	ImageService imageService;
	
	@RequestMapping("/diary")
	public String diary(Principal principal,MemberVO mvo,Model model ) {
		 mvo.setId(principal.getName());
		 model.addAttribute("diary", diaryMapper.getDiaryList());
		 

		return "diary/diaryList";
	}
	
	@GetMapping("/getdiary")
	public String diaryList(Model model) {
	    List<DiaryVO> diaryList = diaryMapper.getDiaryList();
	    model.addAttribute("diaryList", diaryList);
	    return "getdiary";
	}
	
}


