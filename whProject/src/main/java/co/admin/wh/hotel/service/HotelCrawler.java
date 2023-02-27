package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import co.admin.wh.hotel.vo.HotelVO;

public class HotelCrawler implements Crawler {

//	@Autowired private HotelInfoRepository infoRepository;
	
	@Override
	public List<HotelVO> crawling(String url) {

		//Document에 페이지의 모든 소스가 들어감.
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			String str = doc.getElementById("__NEXT_DATA__").html();
			
//			System.out.println("=====================");
//			System.out.println("----"+str); // 얘 json형태로 파싱할 것 !!!!!!!!!!!
//			System.out.println("=====================");

			List<HotelVO> list = new ArrayList<HotelVO>();
			
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(str);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObj2 = (JSONObject)jsonObj.get("props");
				JSONObject jsonObj3 = (JSONObject)jsonObj2.get("pageProps");
				JSONObject jsonObj4 = (JSONObject)jsonObj3.get("apolloState");
				JSONObject jsonObj5 = (JSONObject)jsonObj4.get("ROOT_QUERY");
				JSONObject jsonObj6 = (JSONObject)jsonObj5.get("hotelSearchByPlaceFileName({\"adultCnt\":2,\"checkIn\":\"2023-03-24\",\"checkOut\":\"2023-03-25\",\"childAges\":[],\"includeTax\":false,\"pageIndex\":1,\"placeFileName\":\"place:Seoul\",\"sortDirection\":\"descending\",\"sortField\":\"popularityKR\"})");
				
//				System.out.println(jsonObj5);
//				System.out.println(jsonObj6); // HotelList json
				
				JSONArray infoArr = (JSONArray) jsonObj6.get("hotelList");
//				
//				System.out.println(infoArr); // db에 담을 정보를 배열에 담음
//				
				
				for(int i=0;i<infoArr.size();i++){
                    JSONObject tmp = (JSONObject)infoArr.get(i);
                    HotelVO hotelVO = new HotelVO((String)tmp.get("hotelName"), "5", "hotelAddr", "hotelInfo", "imgGroCode", 9, "hotelContent", 20000); // vo에 담음
                    System.out.println(hotelVO);
                    
                    list.add(hotelVO);
//                    infoRepository.save(infoObj);
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
	}
}
