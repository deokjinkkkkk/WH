package co.admin.wh.notice.web;



import java.io.File;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	@Autowired ServletContext servletContext;
	
	//게시글 리스트 처리
	@RequestMapping("/food")
	public String foodList(Model model, @ModelAttribute("fsvo")FoodSearchVO svo, Paging paging ) {
		paging.setPageUnit(5);//한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); //한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(foodMapper.getCountTotal(svo));
		model.addAttribute("foodList", foodMapper.getFoodList(svo)); 
		
		return "notice/foodList";
	}
	
	//글작성
	@RequestMapping("/foodForm")
	public String foodForm(Model model) {

		return "notice/foodForm";
	}
	
	//파일첨부
	@RequestMapping("/foodJoin.do")
	public String foodJoin(FoodVO vo, Model model, ImageVO ivo, MultipartFile imgFile) {
		
		String saveFolder = servletContext.getRealPath("/img/");
		
		if(!imgFile.isEmpty()) {
			String fileName = UUID.randomUUID().toString();
			fileName = fileName + imgFile.getOriginalFilename();
			File uploadFile = new File(saveFolder, fileName);
			
			try {
				imgFile.transferTo(uploadFile);
			}catch(Exception e) {
				e.printStackTrace();
			}
			ivo.setImgName(imgFile.getOriginalFilename());
			ivo.setImgPath(saveFolder);
		}
		
		foodMapper.foodInsert(vo);
		foodMapper.imgInsert(ivo);
		
			
		return "redirect:food";
	}
	
	//등록
	@RequestMapping("/foodInsert.do")
	public String foodJoin(FoodVO vo, Model model, ImageVO ivo) {
		/* model.addAttribute("foodList", foodMapper.getFoodList(vo)); */
		foodService.foodInsert(vo);
		foodService.imgInsert(ivo);
		
		return "redirect:food";
	}
	
	//게시물 상세보기
	@RequestMapping(value="/foodDetail/{foodCode}", method=RequestMethod.GET)
	public String foodDetail(@PathVariable("foodCode") int foodCode, FoodVO vo, Model model) {
		vo.setFoodCode(foodCode);
		foodService.hitUpdate(foodCode); // 조회수증가
		
		model.addAttribute("food",foodService.detailSelect(vo));
		return "notice/foodDetail";
		
	}
	
	//삭제
	@PostMapping("/foodDelete")
	public String foodDelete(FoodVO vo, RedirectAttributes redirectAttributes) {
		int n = foodService.foodDelete(vo);
		if (n != 0) {
			redirectAttributes.addFlashAttribute("message","정상적으로 삭제되었습니다.");
		}else {
			redirectAttributes.addFlashAttribute("message","삭제가 정상적으로 처리되지 않았습니다.");
		}
		return "redirect:/food";
	}
	
	//수정폼
	@RequestMapping("/foodUpdateForm")
	public String foodUpdateForm(FoodVO vo, Model model) {
		model.addAttribute("food", foodService.detailSelect(vo));
		return "notice/foodUpdateForm";
	}
	
	//수정
	@PostMapping("/foodUpdate")
	public String foodUpdate(@ModelAttribute("foodVO") FoodVO vo, Model model) {
	model.addAttribute("food", foodService.foodUpdate(vo));
	return "redirect:/foodDetail/" + vo.getFoodCode();
	}
}
