package co.admin.wh.trip;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
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

import co.admin.wh.trip.vo.CourseVO;
import co.admin.wh.trip.vo.TripVO;

public class CourseInfoExplorer {

	// tag값의 정보를 가져오는 메소드
	private static String getTagValue(String tag, Element eElement) {
		NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();

	}

	public List<CourseVO> parsingDate(String searchName) throws IOException, ParserConfigurationException, SAXException {

		List<CourseVO> list = new ArrayList<CourseVO>();
		// "&pageNo=100&numOfRows=100" 갯수와 로우 100까지는 테스트 10000은 404에러
		String str = ""; // return을 위해서
		String parsingUrl = "";// Parsing할 URL
		String urlBuilder = "https://apis.data.go.kr/B551011/KorService/areaBasedList?"
				+ URLEncoder.encode("ServiceKey", "UTF-8") + "="
				+ "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D"
				+ /* Service Key */
				"&pageNo=1" + "&numOfRows=1" + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&listYN=Y" + "&arrange=CA"
				+ "&areaCode=33" + "&cat1=C01"; // 추천코스(C01) pair

		URL url = new URL(urlBuilder);

		parsingUrl = url.toString();
		System.out.println(parsingUrl); // api 주소 출력

		// 페이지에 접근해줄 Document객체 생성
		// doc객체를 통해 파싱할 url의 요소를 읽어들인다.
		// doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(parsingUrl);

		// root tag
		doc.getDocumentElement().normalize();
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result

		// 파싱할 데이터 tag에 접근하는데 리스트 수 확인
		NodeList nList = doc.getElementsByTagName("item");
		System.out.println("파싱할 리스트 수 : " + nList.getLength());// 파링할 리스트 수

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				// 코스 고유번호는 계속 파라미터 파고들어야함...
            	String contentid = null;
				Element eElement = (Element) nNode;

				CourseVO vo = new CourseVO();		
				
				// 코스등록일자
				DateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
				java.util.Date courseDate = null;
				
				try {
					courseDate = formatDate.parse(getTagValue("createdtime", eElement).substring(0,8));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				// java.util.Date로 변환한 데이터를 java.sql.Date로 변환
				java.sql.Date createDate = new java.sql.Date(courseDate.getTime());
				
				// 코스수정일자
				DateFormat twoFormatDate = new SimpleDateFormat("yyyyMMdd");
				java.util.Date couDate = null;
				
				try {
					couDate = twoFormatDate.parse(getTagValue("modifiedtime", eElement).substring(0,8));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				java.sql.Date modifieDate = new java.sql.Date(couDate.getTime());
				
				
				vo.setCouCode(getTagValue("contentid", eElement)); // 코스 고유번호
				vo.setCouName(getTagValue("title", eElement)); // 코스 제목
				vo.setRegionCode(Integer.valueOf(getTagValue("areacode", eElement))); // 지역코드
				vo.setCouLat(getTagValue("mapy", eElement)); //위도
				vo.setCouLon(getTagValue("mapx", eElement)); //경도
				vo.setImgGroCode(getTagValue("firstimage2", eElement)); // 이미지
				vo.setCouRegDate(createDate); // 코스등록일자
				vo.setCouModDate(modifieDate); // 코스수정일자
				
				// contentid를 입력해서 밑의 주소 출력
                contentid = getTagValue("contentid", eElement);
                
       
                // ▼ 각 코스(덩어리) 개요 따오는 api
                String parsingUrlChild = "";
                String urlBuilderChild = "https://apis.data.go.kr/B551011/KorService/detailCommon?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" + /*Service Key*/
                        "&MobileOS=ETC" + "&MobileApp=AppTest" +"&contentId=" + contentid
                		+ "&defaultYN=Y" + "&overviewYN=Y";
        			
                URL urlChild = new URL(urlBuilderChild);

                parsingUrlChild = urlChild.toString();
                System.out.println("+++"+parsingUrlChild); // 이 주소에 있는 overview(개요) 필요
                
                //페이지에 접근해줄 Document객체 생성
                //doc객체를 통해 파싱할 url의 요소를 읽어들인다.
                //doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
                DocumentBuilderFactory dbFactoryChild = DocumentBuilderFactory.newInstance();
                
                DocumentBuilder dBuilderChild = dbFactoryChild.newDocumentBuilder();
                
                Document docChild = dBuilderChild.parse(parsingUrlChild);       
                
                // root tag
                docChild.getDocumentElement().normalize();

                //파싱할 데이터  tag에 접근하는데 리스트 수 확인
                NodeList nListChild = docChild.getElementsByTagName("item");
                
                for (int j = 0; j < nListChild.getLength(); j++) {
                    Node nNodeChild = nListChild.item(j);
                    if (nNodeChild.getNodeType() == Node.ELEMENT_NODE) {
                    	Element eElementChild = (Element) nNodeChild;
                    
                        vo.setCouContent(getTagValue("overview", eElementChild)); // 코스설명  
                    	
                    }
                }
                
                
                // 코스 상세정보 api 끌어오기 : 코스길이(distance), 총소요시간(taketime)
                String parsingUrlChildTwo = "";
                String urlBuilderChildTwo = "https://apis.data.go.kr/B551011/KorService/detailIntro?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" + /*Service Key*/
                        "&MobileOS=ETC" + "&MobileApp=AppTest" +"&contentId=" + contentid + "&contentTypeId=25";
        			
                URL urlChildTwo = new URL(urlBuilderChildTwo);

                parsingUrlChildTwo = urlChildTwo.toString();
                System.out.println("---"+parsingUrlChildTwo); // 이 주소에 있는 overview(개요) 필요
                
                //페이지에 접근해줄 Document객체 생성
                //doc객체를 통해 파싱할 url의 요소를 읽어들인다.
                //doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
                DocumentBuilderFactory dbFactoryChildTwo = DocumentBuilderFactory.newInstance();
                
                DocumentBuilder dBuilderChildTwo = dbFactoryChildTwo.newDocumentBuilder();
                
                Document docChildTwo = dBuilderChildTwo.parse(parsingUrlChildTwo);       
                
                // root tag
                docChild.getDocumentElement().normalize();

                //파싱할 데이터  tag에 접근하는데 리스트 수 확인
                NodeList nListChildTwo = docChildTwo.getElementsByTagName("item");
                
                for (int f = 0; f < nListChildTwo.getLength(); f++) {
                    Node nNodeChildTwo = nListChildTwo.item(f);
                    if (nNodeChildTwo.getNodeType() == Node.ELEMENT_NODE) {
                    	
                    	Element eElementChildTwo = (Element) nNodeChildTwo;
                    	
                    	System.out.println("=======================");
                    	System.out.println(getTagValue("distance", eElementChildTwo)); // 코스총거리
                    	System.out.println(getTagValue("taketime", eElementChildTwo)); // 코스 총시간

                    	vo.setCouDist(getTagValue("distance", eElementChildTwo)); // 코스 총거리
                    	
                    }
                }
                
                
				assert false;
				list.add(vo);
			}
		}

		return list;

	}

}
