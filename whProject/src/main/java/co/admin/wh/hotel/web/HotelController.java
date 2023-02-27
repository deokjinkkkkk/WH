package co.admin.wh.hotel.web;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import co.admin.wh.hotel.service.HotelCrawler;
import co.admin.wh.hotel.service.HotelInfoService;
import co.admin.wh.hotel.vo.HotelVO;

@Controller
public class HotelController {
	
	private HotelInfoService hotelInfoService;
	
	@Autowired
    public HotelController(HotelInfoService InfoService) {
        this.hotelInfoService = InfoService;
    }
	
	@GetMapping("/hotel")
	public String hotel(Model model) {
		
		System.out.println("파싱되나용!!");
		
		HotelCrawler crawler = new HotelCrawler();
		
		String url = "https://hotels.naver.com/list?placeFileName=place%3ASeoul&adultCnt=2&checkIn=2023-03-24&checkOut=2023-03-25&includeTax=false&sortField=popularityKR&sortDirection=descending&pageIndex=1";

		List<HotelVO> list = crawler.crawling(url);
		
		for (HotelVO hotelVO : list) {
			try {
				hotelInfoService.insertInfo(hotelVO);
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
		
		model.addAttribute("hotelList",hotelInfoService.hotelList());
		
		System.out.println("파싱 정보 입력 완!");
		
		return "hotel/hotelMain";
	}
}
