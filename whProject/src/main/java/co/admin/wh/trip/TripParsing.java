package co.admin.wh.trip;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TripParsing {

    public static void main(String[] args) {
        try{
        	/*요청정보입력
        	 * 아래와 같은 정보들은 사용자 가이드를 확인하여 찾아주시면 됩니다.
        	 * 위도 경도는 엑셀파일 안에 있습니다.
        	 * */
        	
            //자신이 조회를 원하는 지역코드 + 관광지 특정 정보
            String areaCode = "32";  // 지역코드 32 : 강원도
            String cat1 = "A01";   // 대분류 A01 : 자연
            // 서비스 인증키입니다. 공공데이터포털에서 제공해준 인증키를 넣어주시면 됩니다.
            String serviceKey = "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D";
            
            // 정보를 모아서 URL정보를 만들면됩니다. 맨 마지막 "&_type=json"에 따라 반환 데이터의 형태가 정해집니다.
            String urlStr = "https://apis.data.go.kr/B551011/KorService/areaBasedList?"
            		+ "serviceKey=" + serviceKey + "&numOfRows=15" + "&pageNo=1" + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&_type=json" + "&listYN=Y" + "&arrange=CA"
            		+ "&areaCode="+ areaCode + "&cat1=" + cat1 ;
            URL url = new URL(urlStr); // 위 urlStr을 이용해서 URL 객체를 만들어줍니다.
            BufferedReader bf;
            String line = "";
            String result="";

            //지역관광지 정보를 받아옵니다.
            bf = new BufferedReader(new InputStreamReader(url.openStream()));

            //버퍼에 있는 정보를 하나의 문자열로 변환.
            while((line=bf.readLine())!=null){
                result=result.concat(line);
               System.out.println(result);  // 받아온 데이터를 확인해봅니다.
            }
           
             // Json parser를 만들어 만들어진 문자열 데이터를 객체화 합니다. 
    		JSONParser parser = new JSONParser();
    		JSONObject obj = (JSONObject) parser.parse(result);
    		
    		// Top레벨 단계인 response 키를 가지고 데이터를 파싱합니다.
    		JSONObject parse_response = (JSONObject) obj.get("response");
    		// response 로 부터 body 찾아옵니다.
    		JSONObject parse_body = (JSONObject) parse_response.get("body");
    		// body 로 부터 items 받아옵니다.
    		JSONObject parse_items = (JSONObject) parse_body.get("items");
    		
    		// items로 부터 itemlist 를 받아오기 itemlist : 뒤에 [ 로 시작하므로 jsonarray이다
    		JSONArray parse_item = (JSONArray) parse_items.get("item");
    		
    		JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용합니다.

    		// 필요한 데이터만 가져오려고합니다.
    		for(int i = 0 ; i < parse_item.size(); i++)
    		{
    			weather = (JSONObject) parse_item.get(i);   			
    			System.out.println(weather);
    			
    			String title = (String)weather.get("title");
    			String addr1 = (String)weather.get("addr1");
    			
    			
    			// 출력합니다.
    			System.out.print("배열의 "+i+"번째 요소");
    			System.out.println(title + ":" + addr1);
    			System.out.println();
    		}
    		// 마지막에보면 에러가 발생하였는데 casting문제입니다. 
    		// 이는 반환되는 데이터타입이 달라서인데 이번 글에서는 여기까지만하고
    		// 데이터베이스에 입력할때는 수정해서 하겠습니다.

            bf.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}