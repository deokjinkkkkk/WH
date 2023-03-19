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
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.Paging;

@Controller
public class tourController {
	@Autowired
	TourSerivce tourService;
	
	@Autowired
	ImageService imageService;
	
	@Value("${wh.saveimg}")
	private String saveimg;
	//관광정보요청 폼
	@RequestMapping("/tourReForm")
	public String tourReform(TourVO vo) {
		
		return "tour/tourRequestForm";
	}
	//관광정보요청 여행지에 등록요청
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
	//관리자 관광정보요청 리스트
	@RequestMapping("/adminTourForm")
	public String adminTourForm(TourVO vo,Model model,Paging paging) {
		paging.setPageUnit(5);//한 페이지에 출력할 레코드 건수
		paging.setPageSize(5); //한 페이지에 보여질 페이지 갯수
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		paging.setTotalRecord(tourService.getCountTotal(vo));
		model.addAttribute("tour",tourService.tourList(vo));
		return "admin/tourAdmin";
	}
	//관광정보요청 제목 검색
	@RequestMapping("/tourSearch")
	public String memberSearch(TourVO vo, Model model, Paging paging) {
		paging.setPageUnit(10);//한 페이지에 출력할 레코드 건수
		paging.setPageSize(5); //한 페이지에 보여질 페이지 갯수
		
		System.out.println("PPPPPPPPPPPPPPPPPPPPP"+paging.getFirst() + paging.getLast()+ paging.getPageSize());
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println("PPPPPPPPPPPPPPPPPPPPP"+vo.getFirst() + vo.getLast());
		paging.setTotalRecord(tourService.getCountTotal(vo));
		
		model.addAttribute("tour", tourService.tourSearchList(vo) );
		return "admin/tourAdmin";
		
	}
	//관광정보요청 미수락
	@PostMapping("/tourDelete")
	public String adminTourDelete(TourVO vo) {
		tourService.tourUpdate(vo);
		return "redirect:adminTourForm";
	}
	//관광정보요청 수락
	@PostMapping("/tourTripInsert")
	public String adminTourTripInsert(TourVO vo) {
		tourService.tourUpdate(vo);
		tourService.tourAdminInsert(vo);
		return "redirect:adminTourForm";
	}
	
}
