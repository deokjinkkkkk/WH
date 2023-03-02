package co.admin.wh.trip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import co.admin.wh.trip.vo.TripVO;


public class TripInfoExplorer {

	//tag값의 정보를 가져오는 메소드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if (nValue == null) {
            return null;
        }
        return nValue.getNodeValue();
    }

    public List<TripVO> parsingData(String searchName) throws IOException, ParserConfigurationException, SAXException {

        List<TripVO> list = new ArrayList<TripVO>();
        // "&pageNo=100&numOfRows=100"  갯수와 로우 100까지는 테스트 10000은 404에러
        String str = "";    //return을 위해서
        String parsingUrl = "";//Parsing할 URL
        String urlBuilder = "https://apis.data.go.kr/B551011/KorService/areaBasedList?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" + /*Service Key*/
                "&pageNo=1" + "&numOfRows=50" + "&MobileOS=ETC" + "&MobileApp=AppTest" 
        		+ "&listYN=Y" + "&arrange=CA" + "&areaCode=39" + "&cat1=A01";
			
        URL url = new URL(urlBuilder);

        parsingUrl = url.toString();
        System.out.println(parsingUrl); // api 주소 출력
        
        //페이지에 접근해줄 Document객체 생성
        //doc객체를 통해 파싱할 url의 요소를 읽어들인다.
        //doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        System.out.println(dbFactory);
        
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        System.out.println(dBuilder);
        
        Document doc = dBuilder.parse(parsingUrl);       

        // root tag
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result

        //파싱할 데이터  tag에 접근하는데 리스트 수 확인
        NodeList nList = doc.getElementsByTagName("item");
        System.out.println("파싱할 리스트 수 : " + nList.getLength());//파링할 리스트 수

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {            	
                
            	Element eElement = (Element) nNode;

                TripVO vo = new TripVO();
                          
                System.out.println("=================================");
                System.out.println(getTagValue("title", eElement)); // 관광지 제목
                System.out.println(getTagValue("addr1", eElement)); // 관광지 주소
                System.out.println(getTagValue("tel", eElement)); // 관광지 전화번호
                System.out.println(getTagValue("mapx", eElement)); // 경도
                System.out.println(getTagValue("mapy", eElement)); // 위도
                System.out.println(getTagValue("firstimage2", eElement)); // 관광지 사진
                System.out.println(getTagValue("modifiedtime", eElement).substring(0,8)); // 수정일 -> yyyymmdd 로 출력
                
                        
                DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");              
				java.util.Date tempDate = null;
				
				
				try {
					tempDate = sdFormat.parse(getTagValue("modifiedtime", eElement).substring(0,8));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				java.sql.Date sqlDate = new java.sql.Date(tempDate.getTime()); // java.util.Date로 변환한 데이터를 java.sql.Date로 변환해주기.
                           
                vo.setTripName(getTagValue("title", eElement)); // 관광지 제모
                vo.setTripAddr(getTagValue("addr1", eElement)); // 관광지 주소
                vo.setTripTel(getTagValue("tel", eElement)); // 관광지 전화번호
                vo.setTripLon(getTagValue("mapx", eElement)); // 경도
                vo.setTripLat(getTagValue("mapy", eElement)); // 위도
                vo.setImgGroCode(getTagValue("firstimage2", eElement)); // 관광지 사진
                vo.setTripDate(sqlDate); // 등록일       

                assert false;
                list.add(vo);
            }
        }

        return list;

    }
	
}
