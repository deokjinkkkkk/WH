package co.admin.wh.notice.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.admin.wh.common.service.ImageService;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.NoticeMapper;
import co.admin.wh.notice.service.NoticeService;
import co.admin.wh.notice.vo.NoticeSearchVO;
import co.admin.wh.notice.vo.NoticeVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	ImageService imageService;

	@Autowired
	NoticeMapper noticeMapper;

	@Value("${wh.saveimg}")
	private String saveimg;

	
	@RequestMapping("/notice")
	public String notice(Model model, @ModelAttribute("svo") NoticeSearchVO svo, Paging paging) {

		paging.setPageUnit(5);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		paging.setTotalRecord(noticeService.getCountTotal(svo));
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		model.addAttribute("noticelists", noticeService.getNoticeList(svo));
		return "notice/noticelist";
	}

	//관리자공지사항 
	@RequestMapping("/noticeAdmin")
	public String noticeAdmin(Model model, @ModelAttribute("svo") NoticeSearchVO svo, Paging paging) {

		paging.setPageUnit(5);// 한 페이지에 풀력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수

		paging.setTotalRecord(noticeService.getCountTotal(svo));
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());

		model.addAttribute("noticelists", noticeService.getNoticeList(svo));
		return "admin/noticeListAdmin";
	}
	
	
	@RequestMapping("/noticeForm")
	public String noticeForm(Model model) {
		return "admin/noticeFormAdmin";
	}



	//관리자 등록
	@RequestMapping("/noticeInsert")
	public String noticeInsertAdmin(NoticeVO vo, Model model, ImageVO ivo, MultipartFile[] imgFile) {

		String saveFolder = saveimg; // 파일저장위치

		// 그룹번호 조회
		String image = imageService.imgNotice(ivo);

		// for문
		for (MultipartFile file : imgFile) {
			String fileName = imageService.saveImage(imgFile, saveFolder);
			if (fileName != null) {// 첨부파일이 존재하면 이름UUID해줘서 중복방지해쥼
				// ivo에 담고
				ivo.setImgGroCode(image);
				ivo.setImgName(file.getOriginalFilename()); // 저장할때는 원본파일명
				ivo.setImgPath(fileName); // 물리적 위치 디렉토리포함원본파일명
				noticeMapper.imgInsert(ivo);
				vo.setImgGroCode(ivo.getImgGroCode());
			}
		}
		noticeMapper.noticeInsert(vo);
		return "redirect:/noticeAdmin";
	}
	

	//관리자 상세페이지
	@RequestMapping(value = "/noticeDetails/{noticeCode}", method = RequestMethod.GET)
	public String noticeDetailAdmin(@PathVariable("noticeCode") int noticeCode, NoticeVO vo, Model model) {	
		vo.setNoticeCode(noticeCode);
		//noticeService.noticeHit(noticeCode); // 조회수
		model.addAttribute("n", noticeService.noticendetil(vo));
		return "admin/noticeDetailAdmin";
	}
	
	//회원 상세페이지
	@RequestMapping(value = "/noticeDetail/{noticeCode}", method = RequestMethod.GET)
	public String noticeDe(@PathVariable("noticeCode") int noticeCode, NoticeVO vo, Model model) {	
		vo.setNoticeCode(noticeCode);
		noticeService.noticeHit(noticeCode); // 조회수
		model.addAttribute("n", noticeService.noticendetil(vo));
		return "notice/noticeDetail";
	}
	
	
	//관리자
	@RequestMapping("/noticeUpdateForm")
	public String noticeUpdateForm(@ModelAttribute("n") NoticeVO vo, Model model) {
		model.addAttribute("n", noticeService.noticendetil(vo));
		return "admin/noticeUpdateAdmin";
	}

	//관리자 업로드
	@PostMapping("/noticeUpdate")
	public String noticeUpdate(NoticeVO vo, Model model, ImageVO ivo, MultipartFile[] imgFile) {
		
		String saveFolder = saveimg; // 파일저장위치

		// 그룹번호 조회
		String image = imageService.imgNotice(ivo);
		
		imageService.imageDelete(ivo);
		 model.addAttribute("n", noticeService.noticeUpdate(vo));

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
			noticeMapper.imgInsert(ivo);
			vo.setImgGroCode(ivo.getImgGroCode());
			noticeMapper.noticeDelete(vo);
			noticeMapper.noticeInsert(vo); // 수정된 공지사항 정보를 DB에 반영
		}
		
		return "redirect:/noticeDetails/" + vo.getNoticeCode(); // 수정된 공지사항의 상세 페이지로 이동
	}
	//관리자 삭제
	@PostMapping("/noticeDelete")
	public String noticeDelete(NoticeVO vo, RedirectAttributes redirectAttributes) {
		noticeService.noticeDelete(vo);
		return "redirect:/noticeAdmin";
	}

}
