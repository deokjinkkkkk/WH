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
/*
 * 작성자 :  김지은 
 * 작성일자:2023/03/20
 * 작성내용 : 동행자 게시판
 * */
/**
 * @author admin
 * 동행자 관련 게시판
 * */
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
	MemberMapper memberMapper;

	@Value("${wh.saveimg}")
	private String saveimg;
	
	/**
	 * 
	 * @param model
	 * @param cvo
	 * @param paging
	 * @param ivo
	 * @param vo
	 * @param principal
	 * @return
	 */
	@RequestMapping("/companion") //동행자 목록 가져오기
	public String companion(Model model, 
			               @ModelAttribute("fcvo") CompanionSearchVO cvo, 
			               Paging paging, 
			               MemberVO vo, 
			               Principal principal) {
		
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

	@RequestMapping("/companionForm") //동행자 등록
	public String companionForm(Model model, CommonVO cvo, MemberVO vo, Principal principal) {
		 vo.setId(principal.getName());
		 //id를 가지고 나머지 member값 가져오기
		 model.addAttribute("me", memberMapper.memberSelect(vo));
		 model.addAttribute("co", commomService.commonLocal());
		 model.addAttribute("gr", commomService.commonGroup());
		return "notice/companionForm";
	}

	@RequestMapping("/companionJoin.do") //동행자 등록
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
				ivo.getImgGroCode();
				companionMapper.imgInsert(ivo);
				vo.setImgGroCode(ivo.getImgGroCode());
			}
		}
		companionMapper.companionInsert(vo);
		return "redirect:companion";
	}

	
	@RequestMapping("/companionDetail/{compCode}")//동행자 상세페이지
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
	
	@RequestMapping("/companionUpdateForm") //동행자 업데이트에 보내는 정보
	public String companionUpdateForm(CompanionVO compVO, Model model, CommonVO cvo) {
		 model.addAttribute("c", companionService.detailSelect(compVO));
		 model.addAttribute("co", commomService.commonLocal());
		 model.addAttribute("gr", commomService.commonGroup());
		return "notice/companionUpdateForm";
	}

	@PostMapping("/companionUpdate") //동행자 업데이트
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
				companionMapper.companionDelete(csvo);
		}
		companionMapper.companionInsert(compVO);
		return "redirect:companionDetail/" + compVO.getCompCode();
	}

	@RequestMapping("/companionDelete") //동행자 삭제
	public String companionDelete(CompanionVO compVO, Model model, ImageVO ivo) {
		imageService.imageDelete(ivo);
		companionService.companionDelete(compVO);
		companionService.comSelDelete(compVO);
		return "redirect:companion";
	}
	
	
	@RequestMapping("/comListInsert") //동행자 신청자 등록
	public String comListInsert(CompanionVO compVO) {

		companionMapper.comListInsert(compVO);
		return "redirect:companionDetail/"+compVO.getCompCode();
	}
	
	@RequestMapping("/companionList") //동행자 신청목록
	public String companionList(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("aa", companionService.comSelMyList(vo));
		model.addAttribute("se", companionService.test(vo));
		model.addAttribute("hh", companionService.selTitle(vo));
		return "notice/companionList";
	}
	
	@RequestMapping("/companionSelList") //나에게 신청한 동행자 목록
	public String companionSelList(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("se", companionService.comSelMyList(vo));
		return "notice/companionSelList";
	}
	
	@RequestMapping("/approve") //승인
	public String approve(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		companionService.approve(vo);
		return "redirect:companionList";
	}
	
	@RequestMapping("/reject")//거절
	public String reject(Model model, CompanionVO vo, MemberVO mvo, Principal principal) {
		vo.setId(principal.getName());
		companionService.reject(vo);
		return "redirect:companionList";
	}
}
