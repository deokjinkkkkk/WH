package co.admin.wh.hotel.web;

import co.admin.wh.hotel.service.HotelCrawler;

public class Application {

	public static void main(String[] args) {
		
		HotelCrawler hotel = new HotelCrawler();
		
//		for(int i=1; i<5; i++)
//			hotel.crawling("https://hotels.naver.com/list?placeFileName=place%3ASeoul&adultCnt=2&checkIn=2023-02-22&checkOut=2023-02-23&includeTax=false&sortField=popularityKR&sortDirection=descending&pageIndex="
//							+Integer.toString(i));
		
		hotel.crawling("https://hotels.naver.com/list?placeFileName=place%3ASeoul&adultCnt=2&checkIn=2023-02-24&checkOut=2023-02-25&includeTax=false&sortField=popularityKR&sortDirection=descending&pageIndex=1");
	}
}
