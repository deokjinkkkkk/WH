package co.admin.wh.hotel.service;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HotelCrawler implements Crawler {

	@Override
	public void crawling(String url) {

		//Document에 페이지의 모든 소스가 들어감.
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			System.out.println(doc); // html이 아니라 <script> + json형식 출력
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//<ul class="SearchList_SearchList__CtPf8">인 내용 모두를 가져옴
		Elements element = doc.select("ul.SearchList_SearchList__CtPf8");
		
		System.out.println("==================================");
		//element가 갖고있는 내용 중 <h4 class="Detail_title__40_dz">인 내용 모두를 가져옴
		Iterator<Element> ie1 = element.select("h4.Detail_title__40_dz").iterator();
		
		while(ie1.hasNext()) {
			System.out.println(ie1.next().text());
		}
		System.out.println("=================================");
	}

}
