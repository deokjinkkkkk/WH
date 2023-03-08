package co.admin.wh.diary.web;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.diary.mapper.DiaryMapper;
import co.admin.wh.diary.service.DiaryService;

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
	public String diary(Model model) {
		
		
		
		return "diary/diaryList";
	}
	
}
