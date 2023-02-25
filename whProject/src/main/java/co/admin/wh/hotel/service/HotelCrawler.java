package co.admin.wh.hotel.service;

import java.io.IOException;
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

	@Autowired private SubstationInfoRepository infoRepository;
	
	@Override
	public void crawling(String url) {

		//Document에 페이지의 모든 소스가 들어감.
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			//System.out.println(doc.html());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String str = doc.html();
//		String[] array = str.split(">");
		
//		for(int i=0;i<array.length;i++) {
//			System.out.println(array[i]);
//			System.out.println("------------------------");
//			}
//		
//		System.out.println(array[0]);
		
		//=============================================
//		System.out.println(array[150]);
//		
//		str = array[0].substring(0,array[0].length()-8);
//		
//		System.out.println(str);
		
		//=============================================
//		String[] list = str.split(str, 0)
		
//		String str2 = str.substring(10000,str.length()-24);
//		System.out.println(str2);
		
		//==============================================
		//<ul class="SearchList_SearchList__CtPf8">인 내용 모두를 가져옴
//		Elements element = doc.select("ul.SearchList_SearchList__CtPf8");
		ObjectMapper mapper = new ObjectMapper(); 
		JsonNode node;
//		try {
			String str = doc.getElementById("__NEXT_DATA__").html();
			
//			System.out.println("=====================");
//			System.out.println("----"+str); // 얘 json형태로 파싱할 것 !!!!!!!!!!!
//			System.out.println("=====================");
			
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(str);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObj2 = (JSONObject)jsonObj.get("props");
				JSONObject jsonObj3 = (JSONObject)jsonObj2.get("pageProps");
				JSONObject jsonObj4 = (JSONObject)jsonObj3.get("apolloState");
				JSONObject jsonObj5 = (JSONObject)jsonObj4.get("ROOT_QUERY");
				JSONObject jsonObj6 = (JSONObject)jsonObj5.get("hotelSearchByPlaceFileName({\"adultCnt\":2,\"checkIn\":\"2023-02-24\",\"checkOut\":\"2023-02-25\",\"childAges\":[],\"includeTax\":false,\"pageIndex\":1,\"placeFileName\":\"place:Seoul\",\"sortDirection\":\"descending\",\"sortField\":\"popularityKR\"})");
				
//				System.out.println(jsonObj5);
//				System.out.println(jsonObj6); // HotelList json
				
				JSONArray infoArr = (JSONArray) jsonObj6.get("hotelList");
//				
//				System.out.println(infoArr); // db에 담을 정보를 배열에 담음
//				
				for(int i=0;i<infoArr.size();i++){
		               JSONObject tmp = (JSONObject)infoArr.get(i);
		               HotelVO infoObj = new HotelVO(i, (String)tmp.get("hotelName")); // vo에 담음
		               System.out.println(infoObj);
//		               infoRepository.save(infoObj);
				}
				
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//node = mapper.readValue(doc.select("#__NEXT_DATA__").text(), JsonNode.class );
			//System.out.println(node);
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println("=================================="+element);
//		//element가 갖고있는 내용 중 <h4 class="Detail_title__40_dz">인 내용 모두를 가져옴
//		Iterator<Element> ie1 = element.select("h4.Detail_title__40_dz").iterator();
//		
//		while(ie1.hasNext()) {
//			System.out.println(ie1.next().text());
//		}
//		System.out.println("=================================");
	}

}
