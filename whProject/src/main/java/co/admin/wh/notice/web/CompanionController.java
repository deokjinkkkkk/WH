package co.admin.wh.notice.web;


import java.security.Principal;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.admin.wh.common.mapper.ReportMapper;
import co.admin.wh.common.service.CommonService;
import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.service.ReportService;
import co.admin.wh.common.vo.CommonVO;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.common.vo.ReportVO;
import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.service.CompanionService;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class CompanionController {
	@Autowired
	CompanionMapper companionMapper;

	@Autowired
	CompanionService companionService;

	@Autowired
	ImageService imageService;
	
	@Autowired
	CommonService commomService;
	
	@Autowired
	ReportMapper reportMapper;
	
	@Autowired
	ReportService reportService;

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	MemberMapper memberMapper;

	@Value("${wh.saveimg}")
	private String saveimg;

	@RequestMapping("/companion")
	public String companion(Model model, @ModelAttribute("fcvo") CompanionSearchVO cvo, Paging paging, ImageVO ivo, MemberVO vo, Principal principal) {
		
		//id를 가지고 나머지 member값 가져오기
		vo.setId(principal.getName());
		model.addAttribute("me", memberMapper.memberSelect(vo));
		
		
		paging.setPageUnit(5);
		paging.setPageSize(5);

		cvo.setFirst(paging.getFirst());
		cvo.setLast(paging.getLast());

		paging.setTotalRecord(companionMapper.getCountTotal(cvo));

		model.addAttribute("companionList", companionService.getCompanionList(cvo));
		return "notice/companion";
	}

	@RequestMapping("/companionForm")
	public String companionForm(Model model, CommonVO cvo, MemberVO vo, Principal principal) {
		 vo.setId(principal.getName());
		 System.out.println(principal.getName()+"+++++++++++++++++++++++++++");
			//id를 가지고 나머지 member값 가져오기
			model.addAttribute("me", memberMapper.memberSelect(vo));
		 model.addAttribute("co", commomService.commonLocal());
		 model.addAttribute("gr", commomService.commonGroup());
		return "notice/companionForm";
	}

	@RequestMapping("/companionJoin.do")
	
	public String companionJoin(CompanionVO vo, Model model, ImageVO ivo, MultipartFile[] imgFile, CommonVO cvo) {

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
	public String companionDetail(Model model, CompanionVO compVO, CommonVO cvo, MemberVO vo, Principal principal, ReportVO rvo) {
		
		vo.setId(principal.getName());
		model.addAttribute("me", memberMapper.memberSelect(vo));
		
		model.addAttribute("c", companionService.detailSelect(compVO));
		model.addAttribute("lo", companionService.localSelect(compVO));
		model.addAttribute("r", commomService.commonReport());
		compVO.setId(principal.getName());
		model.addAttribute("se", companionService.comBtn(compVO));
		return "notice/companionDetail";
	}
	
	@RequestMapping("/companionUpdateForm")
	public String companionUpdateForm(CompanionVO compVO, Model model, CommonVO cvo) {
		
		 model.addAttribute("c", companionService.detailSelect(compVO));
		 model.addAttribute("co", commomService.commonLocal());
		 model.addAttribute("gr", commomService.commonGroup());
		return "notice/companionUpdateForm";
	}

	@PostMapping("/companionUpdate")
	public String companionUpdate(CompanionVO compVO, Model model, CommonVO cvo, CompanionSearchVO csvo, ImageVO ivo, MultipartFile[] imgFile) {
		String saveFolder = saveimg; // 파일저장위치

		// 그룹번호 조회
		String image = imageService.imageSelect(ivo);
		
		 
		 imageService.imageDelete(ivo);
		 model.addAttribute("c", companionService.companionUpdate(compVO));
		 
		 
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
				compVO.setImgGroCode(ivo.getImgGroCode());
				companionMapper.companionInsert(compVO);
				
			}
		return "redirect:companionDetail/" + compVO.getCompCode();
	}

	@RequestMapping("/companionDelete")
	public String companionDelete(CompanionVO compVO, Model model, ImageVO ivo) {
		
		/*
		 * File file = new File(ivo.getImgPath()); file.delete();
		 */
		imageService.imageDelete(ivo);
		companionService.companionDelete(compVO);
		return "redirect:companion";
	}
	
	
	@RequestMapping("/comListInsert")
	public String comListInsert(CompanionVO compVO, @RequestParam("compCode") int compCode) {
		compVO.setCompCode(compCode);
		companionMapper.comListInsert(compVO);
		return "redirect:companionDetail/"+compCode;
	}
	
	@RequestMapping("/companionList")
	public String companionList(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		
		model.addAttribute("aa", companionService.comSelMyList(vo));
		model.addAttribute("se", companionService.test(vo));
		
		model.addAttribute("hh", companionService.selTitle(vo));
		return "notice/companionList";
	}
	
	@RequestMapping("/companionSelList")
	public String companionSelList(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		
		vo.setId(principal.getName());
		model.addAttribute("se", companionService.comSelMyList(vo));
		return "notice/companionSelList";
	}
	
	@RequestMapping("/approve")
	public String approve(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("sel", companionService.comSelList(vo));
		
		model.addAttribute("up", companionService.approve(vo));
		return "redirect:companionList";
	}
	
	@RequestMapping("/reject")
	public String reject(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("sel", companionService.comSelList(vo));
		
		model.addAttribute("up", companionService.reject(vo));
		return "redirect:companionList";
	}
}
