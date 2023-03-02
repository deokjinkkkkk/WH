package co.admin.wh.notice.web;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.service.CompanionService;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class CompanionController {
	@Autowired
	private CompanionMapper companionMapper;

	@Autowired
	CompanionService companionService;

	@Autowired
	ImageService imageService;

	@Autowired
	ServletContext servletContext;

	@Value("${wh.saveimg}")
	private String saveimg;

	@RequestMapping("/companion")
	public String companion(Model model, @ModelAttribute("fcvo") CompanionSearchVO cvo, Paging paging, ImageVO ivo) {
		paging.setPageUnit(2);
		paging.setPageSize(5);

		cvo.setFirst(paging.getFirst());
		cvo.setLast(paging.getLast());

		paging.setTotalRecord(companionMapper.getCountTotal(cvo));

//		model.addAttribute("i", companionService.imageSelect(ivo));
		model.addAttribute("companionList", companionService.getCompanionList(cvo));
		return "notice/companion";
	}

	@RequestMapping("/companionForm")
	public String companionForm(Model model) {
		return "notice/companionForm";
	}

	@RequestMapping("/companionJoin.do")
	public String companionJoin(CompanionVO vo, Model model, ImageVO ivo, MultipartFile[] imgFile) {

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
			companionMapper.imgInsert(ivo);
			vo.setImgGroCode(ivo.getImgGroCode());
			companionMapper.companionInsert(vo);
		}
		return "redirect:companion";
	}

	@RequestMapping("/companionDetail/{compCode}")
	public String companionDetail(Model model, CompanionVO compVO) {

		model.addAttribute("c", companionService.detailSelect(compVO));
		return "notice/companionDetail";
	}

	@RequestMapping("/companionUpdateForm")
	public String companionUpdateForm(CompanionVO compVO, Model model) {
		model.addAttribute("c", companionService.detailSelect(compVO));
		return "notice/companionUpdateForm";
	}

//	 @RequestMapping("/companionUpdate") public String companionUpdate(CompanionVO compVO, Model model) { 
//		 model.addAttribute("c",companionService.companionUpdate(compVO)); 
//		 return "redirect:noticeDetail/"+compVO.getCompCode(); }

	@PostMapping("/companionUpdate")
	public String companionUpdate(CompanionVO compVO, Model model) {
		model.addAttribute("c", companionService.companionUpdate(compVO));
		return "redirect:companionDetail/" + compVO.getCompCode();
	}

	@RequestMapping("/companionDelete")
	public String companionDelete(CompanionVO compVO, Model model) {
		companionService.companionDelete(compVO);
		return "redirect:companion";
	}
}
