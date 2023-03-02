package co.admin.wh.hotel.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.hotel.vo.RoomVO;

public class HotelCrawler implements Crawler {
	
	@Override
	public List<HotelVO> hotelCrawling(String url) {

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

			//날짜 구하기
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //원하는 데이터 포맷 지정
			
			//오늘 날짜
			String today = simpleDateFormat.format(cal.getTime()); //지정한 포맷으로 변환 
			
			//내일 날짜
			cal.add(cal.DATE, +1); //날짜를 하루 더한다.
			String tomorrow = simpleDateFormat.format(cal.getTime());
			
			List<HotelVO> hotelList = new ArrayList<HotelVO>();
			
			//json 파싱
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(str);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObj2 = (JSONObject)jsonObj.get("props");
				JSONObject jsonObj3 = (JSONObject)jsonObj2.get("pageProps");
				JSONObject jsonObj4 = (JSONObject)jsonObj3.get("apolloState");
				JSONObject jsonObj5 = (JSONObject)jsonObj4.get("ROOT_QUERY");
				JSONObject jsonObj6 = (JSONObject)jsonObj5.get("hotelSearchByPlaceFileName({\"adultCnt\":2,\"checkIn\":\""+today+"\",\"checkOut\":\""+tomorrow+"\",\"childAges\":[],\"includeTax\":false,\"pageIndex\":1,\"placeFileName\":\"place:Seoul\",\"sortDirection\":\"descending\",\"sortField\":\"popularityKR\"})");
				
				System.out.println(jsonObj5);
//				System.out.println(jsonObj6); // HotelList json
				
				JSONArray infoArr = (JSONArray) jsonObj6.get("hotelList");
//				System.out.println(infoArr); // db에 담을 정보를 배열에 담음
//				
				
				for(int i=0;i<infoArr.size();i++){
                    JSONObject tmp = (JSONObject)infoArr.get(i);
                    
                    //호텔 이미지 가져오기
                    JSONArray images = (JSONArray)tmp.get("images");
//                    System.out.println(images);
                    String image = (String) images.get(0);
                    
                    HotelVO hotelVO = new HotelVO((String)tmp.get("hotelName"), 
                    		(Long)tmp.get("starRating"),
                    		(String)tmp.get("city")+" "+(String)tmp.get("address"), 
                    		"hotelInfo",image,"hotelContent",
                    		(String)tmp.get("city")); // vo에 담음
//                    System.out.println(hotelVO);
                    
                    
                    hotelList.add(hotelVO);
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return hotelList;
	}

	@Override
	public List<RoomVO> roomCrawling(String url) {
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

			//날짜 구하기
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //원하는 데이터 포맷 지정
			
			//오늘 날짜
			String today = simpleDateFormat.format(cal.getTime()); //지정한 포맷으로 변환 
			
			//내일 날짜
			cal.add(cal.DATE, +1); //날짜를 하루 더한다.
			String tomorrow = simpleDateFormat.format(cal.getTime());
			
			List<RoomVO> roomList = new ArrayList<RoomVO>();
			
			//json 파싱
			JSONParser jsonParser = new JSONParser();
			try {
				Object obj = jsonParser.parse(str);
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject jsonObj2 = (JSONObject)jsonObj.get("props");
				JSONObject jsonObj3 = (JSONObject)jsonObj2.get("pageProps");
				JSONObject jsonObj4 = (JSONObject)jsonObj3.get("apolloState");
				JSONObject jsonObj5 = (JSONObject)jsonObj4.get("ROOT_QUERY");
				JSONObject jsonObj6 = (JSONObject)jsonObj5.get("hotelSearchByPlaceFileName({\"adultCnt\":2,\"checkIn\":\""+today+"\",\"checkOut\":\""+tomorrow+"\",\"childAges\":[],\"includeTax\":false,\"pageIndex\":1,\"placeFileName\":\"place:Seoul\",\"sortDirection\":\"descending\",\"sortField\":\"popularityKR\"})");
				
				System.out.println(jsonObj5);
//				System.out.println(jsonObj6); // HotelList json
				
				JSONArray infoArr = (JSONArray) jsonObj6.get("hotelList");
//				System.out.println(infoArr); // db에 담을 정보를 배열에 담음
//				
				
				for(int i=0;i<infoArr.size();i++){
                    JSONObject tmp = (JSONObject)infoArr.get(i);
                    
                    //가격 가져오기
                    JSONArray topRates = (JSONArray)tmp.get("topRates");
                    JSONObject roomInfo = (JSONObject)topRates.get(0);
//                    System.out.println(roomInfo);
                    
                    // 호텔 룸 정보 vo에 넣기.
                    RoomVO roomVO = new RoomVO((String)tmp.get("hotelName"),
                    		(String)roomInfo.get("roomName"),
                    		(long)2,(String)tmp.get("city"),
                    		(long)5,"HO",(long)2);
                    
                    roomList.add(roomVO);
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return roomList;
	}
}
