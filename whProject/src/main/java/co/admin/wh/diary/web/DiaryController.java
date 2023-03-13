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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.diary.mapper.DiaryMapper;
import co.admin.wh.diary.service.DiaryService;
import co.admin.wh.diary.vo.DiaryVO;



@Controller
public class DiaryController {
	@Autowired
	DiaryService service;
	
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
	
	@GetMapping("/getDiaryList/{id}")
	@ResponseBody
	public List<DiaryVO> getDiaryList(@PathVariable("id") String id, Model model) {
	    System.out.println("select 오나?=============");
	    List<DiaryVO> diaryList = diaryMapper.getDiaryList(id);
	    return diaryList;
	}


	@PostMapping("/diaryInsert")
	@ResponseBody
	public Map<String, Object> diaryInsert(DiaryVO vo, String id,ImageVO ivo, MultipartFile[] imgFile ) {
		  System.out.println("insert 오나?=============");
		Map<String, Object> resultMap = new HashMap<>();
		
		String saveFolder = saveimg;//파일저장위치

		//그룹번호 조회
		String image = imageService.diaryImage(ivo);
		
		//for문
		for(MultipartFile file : imgFile) {
			String fileName = imageService.saveImage(imgFile, saveFolder);
		
			if(fileName != null) {
				vo.setImgGroCode(image);
				//이미지 담기 위한
				ivo.setImgGroCode(image);
				ivo.setImgName(file.getOriginalFilename()); //원본파일명으로 저장
				ivo.setImgPath(fileName);//물리적 위치 디렉토리포함 원본파일명
				diaryMapper.imgInsert(ivo);
			}		
		}
		diaryMapper.diaryInsert(vo);
	    return resultMap;
	}
	
	

	@PostMapping("/diaryDelete/{diaryCode}/{id}")
	@ResponseBody
	public Map<String, Object> diaryDelete(DiaryVO vo, @PathVariable("diaryCode") int diaryCode, @PathVariable("id") String id,ImageVO ivo ) {
		  System.out.println("삭제 오나?=============");
		Map<String, Object> resultMap = new HashMap<>();
		
		diaryMapper.diaryDelete(vo);
		
		
		return resultMap;
		
	}
	
	
	@PostMapping("/diaryUpdate/{diaryCode}/{id}")
	@ResponseBody
	public Map<String, Object> diaryUpdate(DiaryVO vo, @PathVariable("diaryCode") int diaryCode, @PathVariable("id") String id ) {
		  System.out.println("수정 오나?=============");
		Map<String, Object> resultMap = new HashMap<>();
		
		diaryMapper.diaryUpdate(vo);
		
		
		return resultMap;
		
	}
	
	
	
}


