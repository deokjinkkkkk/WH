package co.admin.wh.notice.web;



import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.admin.wh.common.service.CommonService;
import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.vo.CommonVO;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.service.FoodService;
import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;
import co.admin.wh.notice.vo.Paging;


@Controller
public class FoodController {
	
	@Autowired FoodMapper foodMapper;
	@Autowired FoodService foodService;
	@Autowired ImageService imageService;
	@Autowired ServletContext servletContext;
	@Autowired 	CommonService commomService;
	
	@Value("${wh.saveimg}")
	private String saveimg;
	
	//게시글 리스트 처리
	@RequestMapping("/food")
	public String foodList(Model model, 
			       		   @ModelAttribute("fsvo")FoodSearchVO svo, 
			       		   Paging paging ) {
		
		paging.setPageUnit(5);//한 페이지에 출력할 레코드 건수
		paging.setPageSize(5); //한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(foodMapper.getCountTotal(svo));
		
		model.addAttribute("foodList", foodMapper.getFoodList(svo)); 
		
		return "notice/foodList";
	}
	
	//글작성
	@RequestMapping("/foodForm")
	public String foodForm(Model model , 
						   CommonVO cvo) {
		
		model.addAttribute("co", commomService.commonLocal());
		model.addAttribute("gr", commomService.commonGroup());
		
		return "notice/foodForm";
	}
	
	//파일첨부
	@RequestMapping("/foodJoin.do")
	public String foodJoin(FoodVO vo, 
						   Model model, 
						   ImageVO ivo, 
						   MultipartFile[] imgFile , 
						   CommonVO cvo) {
		
		String saveFolder = saveimg;//파일저장위치
		
		//그룹번호 조회
		String image = imageService.foodImage(ivo);
		
		//for문
		for(MultipartFile file : imgFile) {
			String fileName = imageService.saveImage(imgFile, saveFolder);
		
			if(fileName != null) {
				ivo.setImgGroCode(image);
				ivo.setImgName(file.getOriginalFilename()); //원본파일명으로 저장
				ivo.setImgPath(fileName);//물리적 위치 디렉토리포함 원본파일명
			}
			ivo.getImgGroCode();
			foodMapper.imgInsert(ivo);
			vo.setImgGroCode(ivo.getImgGroCode());
			foodMapper.foodInsert(vo);
		}
		return "redirect:food";
	}
	
	//등록
	@RequestMapping("/foodInsert.do")
	public String foodJoin(FoodVO vo, 
					       Model model, 
					       ImageVO ivo) {
	
		foodService.foodInsert(vo);
		foodService.imgInsert(ivo);
		
		return "redirect:food";
	}
	
	//게시물 상세보기
	@RequestMapping(value="/foodDetail/{foodCode}", method=RequestMethod.GET)
	public String foodDetail(@PathVariable("foodCode") int foodCode, 
														   FoodVO vo, 
														   Model model) {
		
		vo.setFoodCode(foodCode);
		foodService.hitUpdate(foodCode); // 조회수증가
		
		model.addAttribute("food",foodService.detailSelect(vo));
		model.addAttribute("i", foodService.imgSelect(vo));
		
		return "notice/foodDetail";
		
	}
	
	//삭제
	@PostMapping("/foodDelete")
	@ResponseBody
	public String foodDelete(@RequestBody FoodVO vo) {
		
		int n = foodService.foodDelete(vo);

		return "result";
	}
	
	//수정폼
	@RequestMapping("/foodUpdateForm")
	public String foodUpdateForm(FoodVO vo, 
								Model model) {
		
		model.addAttribute("food", foodService.detailSelect(vo));
		
		return "notice/foodUpdateForm";
	}
	
	//수정
	@PostMapping("/foodUpdate")
	public String foodUpdate(@ModelAttribute("foodVO") FoodVO vo, 
													   ImageVO ivo,
													   MultipartFile[] imgFile) {
		
		// 파일저장위치
		String saveFolder = saveimg; 

			String fileName = imageService.saveImage(imgFile, saveFolder);
			System.out.println(fileName+"============");
			if (fileName != null) {// 첨부파일이 존재하면 이름UUID해줘서 중복방지해쥼
				imageService.imageDelete(ivo);
				
				ivo.setImgName(imgFile[0].getOriginalFilename()); // 저장할때는 원본파일명
				ivo.setImgPath(fileName); // 물리적 위치 디렉토리포함원본파일명
				foodMapper.imgInsert(ivo);
			}
		
		foodService.foodUpdate(vo);
				
		// 수정된 맛집리스트의 상세 페이지로 이동
		return "redirect:/foodDetail/" + vo.getFoodCode();
	}
}
