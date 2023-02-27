package co.admin.wh.trip;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;

import co.admin.wh.trip.service.TripService;

@Controller
public class TripController {

	private TripService tripService;
	
	//생성자 주입
	@Autowired
	public TripController(TripService apiService) {
		this.tripService = apiService;
	}
	
	@RequestMapping("/tripSearch")
	public String tripSearch(Model model) {
		return "trip/tripSearch";
	}
	
	@RequestMapping("/tripDetail")
	public String tripDetail(Model model) {
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
	@GetMapping("/searchList")
	public void searchInfo(Model model) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("파싱 시작");;
		
		TripInfoExplorer apiExplorer = new TripInfoExplorer();
		
		// 파싱하여 리턴한 데이터 값들을 list에 담아주기 위해 사용
		List<TripVO> list = apiExplorer.parsingData("(주)");
		
		// List에 담겨있는 정보들은 db에 넣기 위해서 사용
		for (TripVO tripVO : list) {
			
				try {
					tripService.insertInfo(tripVO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		model.addAttribute("tripList", tripService.tripList());
		
		System.out.println("파싱 끝");
		;
	}
	
}
