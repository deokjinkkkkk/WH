package co.admin.wh.trip.web;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.admin.wh.notice.vo.Paging;
import co.admin.wh.tag.mapper.TagMapper;
import co.admin.wh.tag.service.TagService;
import co.admin.wh.tag.vo.TagVO;
import co.admin.wh.trip.CourseInfoExplorer;
import co.admin.wh.trip.mapper.CourseMapper;
import co.admin.wh.trip.service.CourseService;
import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

@Controller
public class CourseController {

	@Autowired
	private TagService tservice;
	@Autowired
	TagMapper tagMapper;
	private CourseService courseService;
	
	// 생성자 주입
	@Autowired CourseMapper courseMapper;
	@Autowired
	public CourseController(CourseService apiService) {
		this.courseService = apiService;
	}
	
	// 여행 코스 지역 선택
	@RequestMapping("/tripCourseRegion")
	public String tripRegion(Model model, CourseSearchVO cvo, Paging paging) {
		paging.setPageUnit(5);
		paging.setPageSize(10);
		
		cvo.setFirst(paging.getFirst());
		cvo.setLast(paging.getLast());
		
		paging.setTotalRecord(courseService.getCountTotal(cvo));
		
		List<CourseVO> tripCourseSelect = courseService.tripCourseRegion(cvo);
		model.addAttribute("tripCourseSelect",tripCourseSelect);
		
		return "trip/tripCourseRegionList";
		
	}

	// 여행 코스 리스트 출력
	@GetMapping("/course")
	public String courseSearch(Model model, @ModelAttribute("csvo")CourseSearchVO svo, Paging paging) {
		
		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(courseMapper.getCountTotal(svo));
		
		//model.addAttribute("courseList", courseService.courseList(svo));
         model.addAttribute("courseList", courseService.myCouSharing(svo));
    
		//=============코스 list에 tag 인기검색어 띄우기==========
		List<TagVO> tagList = tagMapper.findByTagCnt();
		model.addAttribute("tagList", tagList); 
		//Tag 끝==========================================
		return "trip/tripCourseList";
		
	}	
	
	// 검색시 데이터가 없으면 db에 추가하도록 처리 + 페이지 리스트 처리 페이징
		// *db 저장용 컨트롤러*
		@GetMapping("/courseDb")
		public String courseDb(Model model, @ModelAttribute("csvo")CourseSearchVO svo) throws ParserConfigurationException, SAXException, IOException {
			//System.out.println("파싱 시작");
			CourseInfoExplorer apiExplorer = new CourseInfoExplorer();

			// 파싱하여 리턴한 데이터 값들을 list에 담아주기 위해 사용
			List<CourseVO> list = apiExplorer.parsingDate("");
			
			//List에 담겨있는 정보들은 db에 넣기 위해서 사용, db에 안 넣고 싶을 땐 막아놓기
			for (CourseVO courseVO : list) {
			courseService.insertInfo(courseVO);						
			}
			
			model.addAttribute("courseList", courseService.courseList(svo));
			//System.out.println("파싱 끝");
			return "trip/courseDb";
		}
		// 상세페이지 보기
		@RequestMapping(value = "/courseDetail/{couCode}", method=RequestMethod.GET)
		public String courseDetail(@PathVariable("couCode") String couCode, CourseVO vo, Model model) {
			ObjectMapper object = new ObjectMapper();
			vo.setCouCode(couCode);
			model.addAttribute("onecourse", courseService.oneSelect(vo));
			model.addAttribute("course", courseService.detailSelect(vo));
			try {
				model.addAttribute("couJson", object.writeValueAsString(courseService.detailSelect(vo)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return "trip/tripCourseDetail";
		}
	

//		//==============
//		// 	태그 추가
//		//==============
//	@PostMapping("/saveTags")
//    public ResponseEntity<?> saveTags(@RequestBody List<String> tags) {
//        try {
//            // 전달받은 태그 리스트를 순회하며 DB에 저장
//            for (String tag : tags) {
//            	tservice.saveTag(tag);
//            }
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save tags");
//        }
//    }
	
}
