package co.admin.wh.trip.web;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import co.admin.wh.notice.vo.Paging;
import co.admin.wh.trip.CourseInfoExplorer;
import co.admin.wh.trip.mapper.CourseMapper;
import co.admin.wh.trip.service.CourseService;
import co.admin.wh.trip.vo.CourseSearchVO;
import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.MyCourseVO;

@Controller
public class CourseController {

	private CourseService courseService;
	
	// 생성자 주입
	@Autowired CourseMapper courseMapper;
	@Autowired
	public CourseController(CourseService apiService) {
		this.courseService = apiService;
	}
	
	
	// 지역 목록(서울, 대전, 대구 등등)
	@RequestMapping("/regionList")
	public String regionlist(Model model) {
		return "trip/tripCourse";
	}
	
	// 지역리스트 출력
	@GetMapping("/courseList")
	public String courseList(Model model, @ModelAttribute("csvo")CourseSearchVO svo, Paging paging) {
		
		paging.setPageUnit(5);
		paging.setPageSize(10);
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(courseMapper.getCountTotal(svo));
		
		model.addAttribute("regionList", courseService.regionList(svo));
		return "trip/tripRegionList";
	}
	
	
	// 여행 코스 리스트 출력
	@GetMapping("/course")
	public String courseSearch(Model model, @ModelAttribute("csvo")CourseSearchVO svo, Paging paging) {
		
		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(courseMapper.getCountTotal(svo));
		
		model.addAttribute("courseList", courseService.courseList(svo));
		return "trip/tripcourseList";
		
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
		vo.setCouCode(couCode);
		model.addAttribute("onecourse", courseService.oneSelect(vo));
		model.addAttribute("course", courseService.detailSelect(vo));
		return "trip/tripCourseDetail";
	}
}
