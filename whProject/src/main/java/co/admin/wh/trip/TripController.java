package co.admin.wh.trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TripController {
	
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
	
	@GetMapping("/apiTest")
	public String apiTest() throws IOException{
		
		StringBuilder result = new StringBuilder();
		
	
			String urlstr = "https://apis.data.go.kr/B551011/Durunubi/courseList?" +
					"serviceKey=5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" +
					"&numOfRows=10" +
					"&pageNo=1" +
					"&MobileOS=ETC" +
					"&MobileApp=AppTest" +
					"&routeIdx=" +
					"&_type=json";
			
			URL url = new URL(urlstr);
			
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			
			BufferedReader br;
			
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			
			String returnLine;
			
			while((returnLine = br.readLine()) != null) {
				result.append(returnLine+"\n\r");
			}
			
			urlConnection.disconnect();
		
		return result.toString();
	}
	
	
	@GetMapping("/apiTest2")
	public String apiTest2() throws IOException{ // ex) 강원도 자연 여행지
		
		StringBuilder result = new StringBuilder();
		
	
			String urlstr = "https://apis.data.go.kr/B551011/KorService/areaBasedList?"
					+ "serviceKey=5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D"
					+ "&numOfRows=30" 
					+ "&pageNo=1"
					+ "&MobileOS=ETC"
					+ "&MobileApp=AppTest"
					+ "&_type=json"
					+ "&listYN=Y"
					+ "&arrange=CA"
					+ "&areaCode=32"
					+ "&cat1=A01";
			
			URL url = new URL(urlstr);
			
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			
			BufferedReader br;
			
			br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			
			String returnLine;
			
			while((returnLine = br.readLine()) != null) {
				result.append(returnLine+"\n\r");
			}
			
			urlConnection.disconnect();
		
		return result.toString();
	}
}
