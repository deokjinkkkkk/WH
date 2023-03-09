package co.admin.wh.diary.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.vo.ImageVO;
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
		 model.addAttribute("diarys", diaryMapper.getDiaryList());
		return "diary/diaryList";
	}
	
	@GetMapping("/getDiaryList")
	@ResponseBody
	public List<DiaryVO> getDiaryList(DiaryVO vo, Model model) {
	  List<DiaryVO> diaryList = diaryMapper.getDiaryList();
	  model.addAttribute("diarys", diaryMapper.getDiaryList());
	  return diaryList;
	}
	

	@PostMapping("/diaryInsert")
	@ResponseBody
	public Map<String, Object> diaryInsert(@RequestBody Map<String, Object> data) {
	    Map<String, Object> resultMap = new HashMap<>();

	    DiaryVO vo = new ObjectMapper().convertValue(data.get("vo"), DiaryVO.class);
	    ImageVO ivo = new ObjectMapper().convertValue(data.get("ivo"), ImageVO.class);
	    String id = (String) data.get("id");
	    MultipartFile[] imgFile = (MultipartFile[]) data.get("imgFile");

	    diaryMapper.diaryInsert(vo);

	    return resultMap;
	}
}


