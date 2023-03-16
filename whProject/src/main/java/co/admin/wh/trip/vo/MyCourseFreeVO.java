package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class MyCourseFreeVO {

	// 나만의 코스 상세페이지
	int tripUniqueNumber; // 여행코드
	int myCourseCode; // 나만의코스코드...
	String couOrd; // 순서
	int myCouFreeCode; // 나만의코스지역번호
	
	String tripName; // 여행지명
	String imgGroCode; // 이미지
	String tripAddr; // 주소
	String tripLat; //위도
	String tripLon; //경도

	String myCouIntro; // 코스 소개글

}
