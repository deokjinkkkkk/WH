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

	// tag값의 정보를 가져오는 메소드
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

		// "&pageNo=100&numOfRows=100" 갯수와 로우 100까지는 테스트 10000은 404에러
		// 일단은 각 지역의 여행지를 끌어와보자.
		String str = ""; // return을 위해서
		String parsingUrl = "";// Parsing할 URL
		String urlBuilder = "https://apis.data.go.kr/B551011/KorService/areaBasedList?"
				+ URLEncoder.encode("ServiceKey", "UTF-8") + "="
				+ "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D"
				+ /* Service Key */
				"&pageNo=1" + "&numOfRows=33" + "&MobileOS=ETC" + "&MobileApp=AppTest" + "&listYN=Y" + "&arrange=CA"
				+ "&areaCode=34" + "&cat1=C01"; // 추천코스(C01) 에서 여행지 소스 담기.

		URL url = new URL(urlBuilder);

		parsingUrl = url.toString();
		//System.out.println(parsingUrl); // api 주소 출력

		// 페이지에 접근해줄 Document객체 생성
		// doc객체를 통해 파싱할 url의 요소를 읽어들인다.
		// doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document doc = dBuilder.parse(parsingUrl);

		// root tag
		doc.getDocumentElement().normalize();
		//System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result

		// 파싱할 데이터 tag에 접근하는데 리스트 수 확인
		NodeList nList = doc.getElementsByTagName("item");
		//System.out.println("파싱할 리스트 수 : " + nList.getLength());// 파링할 리스트 수

        for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				String contentid = null;
				Element eElement = (Element) nNode;


				//vo.setCouCode(getTagValue("contentid", eElement)); // 코스고유번호

//				System.out.println(getTagValue("contentid", eElement));
//				System.out.println(getTagValue("title", eElement));
				
				// 추천 코스의 contentid를 주입해서 관련 여행지 api 주소 출력...
				contentid = getTagValue("contentid", eElement);
                String parsingUrlchild = "";// Parsing할 URL
				String urlBuilderchild = "https://apis.data.go.kr/B551011/KorService/detailInfo?"
						+ URLEncoder.encode("ServiceKey", "UTF-8") + "="
						+ "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" /*																						 */
						+ "&MobileOS=ETC" + "&MobileApp=AppTest" + "&contentId=" + contentid + "&contentTypeId=25";

				URL urlchild = new URL(urlBuilderchild);

				// 이 주소에 있는 코스여행순서(subnum), 개요 (subdetailoverview) 필요
				parsingUrlchild = urlchild.toString();
				//System.out.println("+++" + parsingUrlchild);

				// 페이지에 접근해줄 Document객체 생성
				// doc객체를 통해 파싱할 url의 요소를 읽어들인다.
				// doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
				DocumentBuilderFactory dbFactorychild = DocumentBuilderFactory.newInstance();

				DocumentBuilder dBuilderchild = dbFactorychild.newDocumentBuilder();

				Document docchild = dBuilderchild.parse(parsingUrlchild);

				// root tag
				docchild.getDocumentElement().normalize();

				// 파싱할 데이터 tag에 접근하는데 리스트 수 확인
				NodeList nListchild = docchild.getElementsByTagName("item");
				//System.out.println("파싱할 리스트 수 2차 : " + nListchild.getLength());// 파링할 리스트 수


				for (int j = 0; j < nListchild.getLength(); j++) {
					Node nNodechild = nListchild.item(j);
					if (nNodechild.getNodeType() == Node.ELEMENT_NODE) {

						TripVO vo = new TripVO();
						
						String subContentid = null;
						Element eElementchild = (Element) nNodechild;
						

						// 가져와야할 정보 : 코스여행순서(subnum), 개요 (subdetailoverview)
						vo.setCouCode(getTagValue("contentid", eElementchild)); // 코스고유번호
						vo.setCouOrder(Integer.valueOf(getTagValue("subnum", eElementchild))); // 코스여행순서
						vo.setTripSumm(getTagValue("subdetailoverview", eElementchild)); // 개요
						
						// 여행지 코드번호(subcontentid) for문 돌려서 여행지 상세정보 가져오기.
		                subContentid = getTagValue("subcontentid", eElementchild);

								String parsingUrlchildTwo = "";// Parsing할 URL
								String urlBuilderchildTwo = "https://apis.data.go.kr/B551011/KorService/detailCommon?"
										+ URLEncoder.encode("ServiceKey", "UTF-8") + "="
										+ "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" /*																													 */
										+ "&MobileOS=ETC" + "&MobileApp=AppTest" + "&contentId=" + subContentid
										+ "&contentTypeId=" + "&defaultYN=Y" + "&firstImageYN=Y" + "&areacodeYN=Y"
										+ "&catcodeYN=Y" + "&addrinfoYN=Y" + "&mapinfoYN=Y" + "&overviewYN=Y";

								URL urlchildTwo = new URL(urlBuilderchildTwo);

								// 이 주소에 있는 코스여행순서(subnum), 개요 (subdetailoverview) 필요
								parsingUrlchildTwo = urlchildTwo.toString();
								//System.out.println("---" + parsingUrlchildTwo);

								// 페이지에 접근해줄 Document객체 생성
								// doc객체를 통해 파싱할 url의 요소를 읽어들인다.
								// doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
								DocumentBuilderFactory dbFactorychildTwo = DocumentBuilderFactory.newInstance();

								DocumentBuilder dBuilderchildTwo = dbFactorychildTwo.newDocumentBuilder();

								Document docchildTwo = dBuilderchildTwo.parse(parsingUrlchildTwo);

								// root tag
								docchildTwo.getDocumentElement().normalize();

								// 파싱할 데이터 tag에 접근하는데 리스트 수 확인
								NodeList nListchildTwo = docchildTwo.getElementsByTagName("item");
								//System.out.println("파싱할 리스트 수 3차 : " + nListchildTwo.getLength());// 파링할 리스트 수

										
								for (int f = 0; f < nListchildTwo.getLength(); f++) {
									Node nNodechildTwo = nListchildTwo.item(f);
									if (nNodechildTwo.getNodeType() == Node.ELEMENT_NODE) {

										Element eElementchildTwo = (Element) nNodechildTwo;
										
										// 코스수정일자
										DateFormat FormatDate = new SimpleDateFormat("yyyyMMdd");
										java.util.Date tripDate = null;
										
										try {
											tripDate = FormatDate.parse(getTagValue("modifiedtime", eElement).substring(0,8));
										} catch (ParseException e) {
											e.printStackTrace();
										}
										
										java.sql.Date showDate = new java.sql.Date(tripDate.getTime());

										/*
										 * 가져와야할 정보 : 여행번호(tripCode, contentid), 명칭(title), 연락처(tel), 주소(addr1), 위도/경도,
										 * 상세정보(tripContent, overview), 수정일자(tripDate, modifiedtime) 지역코드(tripRegion,
										 * areaCode), 이미지
										 */
										
										vo.setTripName(getTagValue("title", eElementchildTwo)); // 여행지 명칭
										vo.setTripTel(getTagValue("tel", eElementchildTwo)); // 연락처
										vo.setTripAddr(getTagValue("addr1", eElementchildTwo)); // 주소
										vo.setTripLat(getTagValue("mapy", eElementchildTwo)); // 위도
										vo.setTripLon(getTagValue("mapx", eElementchildTwo)); // 경도
										vo.setTripContent(getTagValue("overview", eElementchildTwo)); // 상세정보
										vo.setTripRegion(getTagValue("areacode", eElementchildTwo)); // 지역번호
										vo.setImgGroCode(getTagValue("firstimage2", eElementchildTwo)); // 이미지
										vo.setTripDate(showDate); // 수정일자
										vo.setTripUniqueNumber(Integer.valueOf(getTagValue("contentid", eElementchildTwo))); // test 여행지 번호
										
										// 각 여행지 이용안내 api
										String parsingUrlchildThr = "";// Parsing할 URL
										String urlBuilderchildThr = "https://apis.data.go.kr/B551011/KorService/detailIntro?"
												+ URLEncoder.encode("ServiceKey", "UTF-8") + "="
												+ "5gtCcmZt9I035nXIlRn1NfxTbfivYkN69cghQlZ5EGLSe%2FvYaLMhXG%2B3bN1fQ%2F2BASibMcSqEouIrIyqNT64Eg%3D%3D" /*																													 */
												+ "&MobileOS=ETC" + "&MobileApp=AppTest" + "&contentId=" + subContentid
												+ "&&contentTypeId=12";

										URL urlchildThr = new URL(urlBuilderchildThr);

										// 이 주소에 있는 코스여행순서(subnum), 개요 (subdetailoverview) 필요
										parsingUrlchildThr = urlchildThr.toString();
										//System.out.println("???" + parsingUrlchildThr);

										// 페이지에 접근해줄 Document객체 생성
										// doc객체를 통해 파싱할 url의 요소를 읽어들인다.
										// doc.getDocumentElement().getNodeName()을 출력하면 위 xml의 최상위 태그를 가져온다.
										DocumentBuilderFactory dbFactorychildThr = DocumentBuilderFactory.newInstance();

										DocumentBuilder dBuilderchildThr = dbFactorychildThr.newDocumentBuilder();

										Document docchildThr = dBuilderchildThr.parse(parsingUrlchildThr);

										// root tag
										docchildThr.getDocumentElement().normalize();

										// 파싱할 데이터 tag에 접근하는데 리스트 수 확인
										NodeList nListchildThr = docchildThr.getElementsByTagName("item");
										//System.out.println("파싱할 리스트 수 4차 : " + nListchildThr.getLength());// 파링할 리스트 수
										
										
										for (int g = 0; g < nListchildThr.getLength(); g++) {
											Node nNodechildThr = nListchildThr.item(g);
											if (nNodechildThr.getNodeType() == Node.ELEMENT_NODE) {
												
												Element eElementchildThr = (Element) nNodechildThr;
												
//												System.out.println("==??????????????????==");
//												System.out.println(getTagValue("infocenter", eElementchildThr)); // 안내
//												System.out.println(getTagValue("parking", eElementchildThr)); // 주차가능
												
												vo.setTripAnnounce(getTagValue("infocenter", eElementchildThr)); // 문의 및 안내
												vo.setRestDate(getTagValue("restdate", eElementchildThr)); // 쉬는 날
												vo.setUseTime(getTagValue("usetime", eElementchildThr)); // 이용시간
												vo.setTripParking(getTagValue("parking", eElementchildThr)); // 주차시설
												vo.setTripChild(getTagValue("chkbabycarriage", eElementchildThr)); // 유모차 대여여부
												vo.setTripPet(getTagValue("chkpet", eElementchildThr)); // 애완동물 동반가능 여부
												vo.setTripChkCard(getTagValue("chkcreditcard", eElementchildThr)); // 신용카드 가능 여부
												
												
											}
										
										}
											
										list.add(vo);
									}
								}

					}
				}


            }
         
        }

		return list;

	}

}