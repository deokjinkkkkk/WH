package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class HotelCrawler implements Crawler {

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
			
			System.out.println("=====================");
			System.out.println("----"+str); // 얘 json형태로 파싱할 것 !!!!!!!!!!!
			System.out.println("=====================");
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
