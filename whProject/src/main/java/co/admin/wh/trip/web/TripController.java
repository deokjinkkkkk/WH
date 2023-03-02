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
import co.admin.wh.trip.TripInfoExplorer;
import co.admin.wh.trip.mapper.TripMapper;
import co.admin.wh.trip.service.TripService;
import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;

@Controller
public class TripController {

	private TripService tripService;
	
	//생성자 주입
	@Autowired TripMapper tripMapper;
	@Autowired
	public TripController(TripService apiService) {
		this.tripService = apiService;
	}
	
	@RequestMapping(value = "/tripDetail/{tripCode}", method=RequestMethod.GET)
	public String tripDetail(@PathVariable("tripCode") int tripCode, TripVO vo, Model model) { // 상세페이지 보기		
		vo.setTripCode(tripCode);
		
		model.addAttribute("trip", tripService.detailSelect(vo));
		return "trip/tripDetail";
	}
	
	@RequestMapping("/tripCourse")
	public String tripCourse(Model model) {
		return "trip/tripCourse";
	}
	
	@RequestMapping("/memberCourse")
	public String memberCourse(Model model) {
		return "trip/memberCourse";
	}
	
	@GetMapping("/memberCourseDetail")
	public String memberCourseDetail(Model model) {
		return "trip/memberCourseDetail";
	}
	
	
	// 검색시 데이터가 없으면 db에 추가하도록 처리
	// + 페이지 리스트 처리 페이징
	@GetMapping("/trip")
	public String tripSearch(Model model, @ModelAttribute("tsvo")TripSearchVO svo, Paging paging) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("파싱 시작");;
		
		paging.setPageUnit(5); // 한 페이지에 출력할 레코드 건수
		paging.setPageSize(10); // 한 페이지에 보여질 페이지 갯수
		
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		
		paging.setTotalRecord(tripMapper.getCountTotla(svo));
		model.addAttribute("tripLittleList", tripMapper.tripList(svo));
		
		
		TripInfoExplorer apiExplorer = new TripInfoExplorer();
		
		// 파싱하여 리턴한 데이터 값들을 list에 담아주기 위해 사용
		List<TripVO> list = apiExplorer.parsingData("");
		System.out.println("list = " + list);
		
		// List에 담겨있는 정보들은 db에 넣기 위해서 사용, db에 안 넣고 싶을 땐 막아놓기
//		for (TripVO tripVO : list) {
//		tripService.insertInfo(tripVO);						
//		}
		
		model.addAttribute("tripList", tripService.tripList(svo));
		
		System.out.println("파싱 끝");
		return "trip/tripSearch";
	}
	
}
