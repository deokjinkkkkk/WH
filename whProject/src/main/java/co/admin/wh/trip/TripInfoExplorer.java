package co.admin.wh.trip;

import java.io.IOException;
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
                "&pageNo=1" + "&numOfRows=30" + "&MobileOS=ETC" + "&MobileApp=AppTest" 
        		+ "&listYN=Y" + "&arrange=CA" + "&areaCode=39" + "&cat1=A01";
			
        URL url = new URL(urlBuilder);

        parsingUrl = url.toString();
        System.out.println(parsingUrl); // api 주소 출력
        
        
        //페이지에 접근해줄 Document객체 생성
        //doc객체를 통해 파싱할 url의 요소를 읽어들인다.
        //doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
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
                
            	String contentid = null;
            	Element eElement = (Element) nNode;

                TripVO vo = new TripVO();
                          
                System.out.println("=================================");
                System.out.println(getTagValue("title", eElement)); // 관광지 제목
                System.out.println(getTagValue("addr1", eElement)); // 관광지 주소
                System.out.println(getTagValue("tel", eElement)); // 관광지 전화번호
                System.out.println(getTagValue("mapx", eElement)); // 경도
                System.out.println(getTagValue("mapy", eElement)); // 위도
                                           
                
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
                
                
                // contentid를 입력해서 api 주소 출력...
                contentid = getTagValue("contentid", eElement);
                
                String parsingUrl1 = "";//Parsing할 URL
                String urlBuilder1 = "https://apis.data.go.kr/B551011/KorService/detailCommon?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" + /*Service Key*/
                        "&MobileOS=ETC" + "&MobileApp=AppTest" +"&contentId=" + contentid
                		+ "&defaultYN=Y" + "&addrinfoYN=Y" + "&overviewYN=Y";
        			
                URL url1 = new URL(urlBuilder1);

                parsingUrl1 = url1.toString();
                System.out.println("+++"+parsingUrl1); // 이 주소에 있는 overview(개요)와 hompage(홈페이지 주소)가 필요.
                
                //페이지에 접근해줄 Document객체 생성
                //doc객체를 통해 파싱할 url의 요소를 읽어들인다.
                //doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
                DocumentBuilderFactory dbFactory1 = DocumentBuilderFactory.newInstance();
                
                DocumentBuilder dBuilder1 = dbFactory1.newDocumentBuilder();
                
                Document doc1 = dBuilder1.parse(parsingUrl1);       
                
                // root tag
                doc1.getDocumentElement().normalize();

                //파싱할 데이터  tag에 접근하는데 리스트 수 확인
                NodeList nList1 = doc1.getElementsByTagName("item");
                
                for (int j = 0; j < nList1.getLength(); j++) {
                    Node nNode1 = nList1.item(j);
                    if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                    	Element eElement1 = (Element) nNode1;
                    	
                    	// 홈페이지 필요한 부분만 문자 잘라내고 db 입력하기
                    	String test = null;
                    	test = getTagValue("homepage", eElement1).substring(getTagValue("homepage", eElement1).lastIndexOf("http"));                	
                    	int start = test.indexOf("h");
                    	int end = test.indexOf("<");
                    	String startToend = test.substring(start, end);
                    	
                    	System.out.println(startToend); // 홈페이지 주소
                    	
                    	vo.setTripSumm(getTagValue("overview", eElement1)); // 개요
                    	vo.setHomepage(startToend); // 홈페이지 주소
                    	         	
                    }
                }
                
                
                assert false;
                list.add(vo);
            }
        }

        return list;

    }
	
}
