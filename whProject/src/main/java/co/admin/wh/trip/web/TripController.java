package co.admin.wh.trip.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xml.sax.SAXException;

import co.admin.wh.notice.vo.Paging;
import co.admin.wh.trip.TripInfoExplorer;
import co.admin.wh.trip.mapper.TripMapper;
import co.admin.wh.trip.service.TripService;
import co.admin.wh.trip.vo.MyCourseFreeVO;
import co.admin.wh.trip.vo.TripSearchVO;
import co.admin.wh.trip.vo.TripVO;


/*
* 작성자 : 정선희 
* 작성일자 : 2023-03-20 
* 작성내용 : 여행지 Controller
* 		   여행지 목록 출력 / 상세페이지 보기 / 여행지 지역 선택 / 여행지 이름 검색 / 
* 		   최신순, 좋아요순 정렬 / 여행지 DB 저장용 
*/

@Controller
public class TripController {

	private TripService tripService;
	
	@Autowired TripMapper tripMapper;
	
	//생성자 주입
	@Autowired
	public TripController(TripService apiService) {
		this.tripService = apiService;
	}
	
	// 상세페이지 보기
	@RequestMapping(value = "/tripDetail/{tripCode}", method=RequestMethod.GET)
	public String tripDetail(@PathVariable("tripCode") int tripCode, TripVO vo, Model model) { // 상세페이지 보기		
		vo.setTripCode(tripCode);		
		model.addAttribute("trip", tripService.detailSelect(vo));
		model.addAttribute("myCou", tripService.myCouNameSelect(vo));
		return "trip/tripDetail";
	}
	

	// 여행지 목록
	@GetMapping("/trip")
	public String tripSearch(Model model, @ModelAttribute("tsvo")TripSearchVO svo, Paging paging) {
		return "trip/tripList";
	}
	
	
	// 여행지 지역 선택
	@RequestMapping("/tripRegion")
	public String tripRegion(Model model, TripSearchVO tvo, Paging paging) {
		paging.setPageUnit(5);
		paging.setPageSize(10);
		
		tvo.setFirst(paging.getFirst());
		tvo.setLast(paging.getLast());
		
		paging.setTotalRecord(tripService.getCountTotla(tvo));
		
		List<TripVO> tripSelect = tripService.tripRegion(tvo);
		model.addAttribute("tripSelect",tripSelect);
		
		return "trip/tripRegionList";
		
	}
	
	// 최신순 정렬
	@PostMapping("/latestList")
	public String latestList(Model model, TripSearchVO vo, Paging paging) {
		paging.setPageUnit(10);
		paging.setPageSize(10);
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(tripService.getCountTotla(vo));
		List<TripVO> tripList = tripService.latestList(vo);
		model.addAttribute("tripList", tripList);
		return "trip/sortingTripList";
	}
	
	// 좋아요순 정렬
	@PostMapping("/tripGoodRatingList")
	public String tripGoodRatingList(Model model, TripSearchVO vo, Paging paging) {
		paging.setPageUnit(5);
		paging.setPageSize(10);
		
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		
		paging.setTotalRecord(tripService.getCountTotla(vo));
		List<TripVO> tripList = tripService.tripGoodRatingList(vo);
		model.addAttribute("tripList", tripList);
		return "trip/sortingTripList";
	}
	
	// 검색시 데이터가 없으면 db에 추가하도록 처리 + 페이지 리스트 처리 페이징
	// *db 저장용 컨트롤러*
	@GetMapping("/tripDb")
	public String tripSearch(Model model, @ModelAttribute("tsvo")TripSearchVO svo) throws ParserConfigurationException, SAXException, IOException {
		
		//System.out.println("파싱 시작");
		
		TripInfoExplorer apiExplorer = new TripInfoExplorer();
		
		// 파싱하여 리턴한 데이터 값들을 list에 담아주기 위해 사용
		List<TripVO> list = apiExplorer.parsingData("");
				
		 //List에 담겨있는 정보들은 db에 넣기 위해서 사용, db에 안 넣고 싶을 땐 막아놓기
		for (TripVO tripVO : list) {
		tripService.insertInfo(tripVO);						
		}

		model.addAttribute("tripList", tripService.tripList(svo));
		
		//System.out.println("파싱 끝");
		return "trip/tripDb";
	}

	
}
