package co.admin.wh.common.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.service.TourSerivce;
import co.admin.wh.common.vo.CommonVO;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.common.vo.TourVO;

@Controller
public class tourController {
	@Autowired
	TourSerivce tourService;
	
	@Autowired
	ImageService imageService;
	
	@Value("${wh.saveimg}")
	private String saveimg;
	
	@RequestMapping("/tourReForm")
	public String tourReform(TourVO vo) {
		
		return "tour/tourRequestForm";
	}
	@PostMapping("/tourInsert")
	public String tourInsert(TourVO vo, Model model, ImageVO ivo, MultipartFile[] imgFile, CommonVO cvo) {
		String saveFolder = saveimg; // 파일저장위치

		// 그룹번호 조회
		String image = imageService.imageSelect(ivo);
		
		// for문
		for (MultipartFile file : imgFile) {
			String fileName = imageService.saveImage(imgFile, saveFolder);
			if (fileName != null) {// 첨부파일이 존재하면 이름UUID해줘서 중복방지해쥼
				// ivo에 담고
				ivo.setImgGroCode(image);
				ivo.setImgName(file.getOriginalFilename()); // 저장할때는 원본파일명
				ivo.setImgPath(fileName); // 물리적 위치 디렉토리포함원본파일명
			}
			ivo.getImgGroCode();
			tourService.imgInsert(ivo);
			vo.setImgGroCode(ivo.getImgGroCode());
			tourService.tourInsert(vo);
			
		}
		return "content/main";
	}
	@RequestMapping("adminTourForm")
	public String adminTourForm(TourVO vo,Model model) {
		
		model.addAttribute("tour",tourService.tourList());
		return "admin/tourAdmin";
	}
	
	
	@PostMapping("/tourDelete")
	public String adminTourDelete(TourVO vo) {
		tourService.tourUpdate(vo);
		return "redirect:adminTourForm";
	}
	
	@PostMapping("/tourTripInsert")
	public String adminTourTripInsert(TourVO vo) {
		tourService.tourUpdate(vo);
		tourService.tourAdminInsert(vo);
		return "redirect:adminTourForm";
	}
	
}
