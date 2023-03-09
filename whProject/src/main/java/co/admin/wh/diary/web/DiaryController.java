package co.admin.wh.diary.web;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.diary.mapper.DiaryMapper;
import co.admin.wh.diary.vo.DiaryVO;



@Controller
public class DiaryController {
	
	@Autowired
	DiaryMapper diaryMapper;
	
	@Autowired
	ServletContext servletContext;
	
	@Value("${wh.saveimg}")
	private String saveimg;

	@Autowired
	ImageService imageService;
	
	@RequestMapping("/diary") //이동
	public String diary(Model model) {
		 model.addAttribute("d", diaryMapper.getDiaryList());
		return "diary/diaryList";
	}
	
	@GetMapping("/getDiaryList")
	@ResponseBody
	public List<DiaryVO> getDiaryList(DiaryVO vo, Model model) {
	  List<DiaryVO> diaryList = diaryMapper.getDiaryList();
	  model.addAttribute("d", diaryMapper.getDiaryList());
	  return diaryList;
	}
	
}


