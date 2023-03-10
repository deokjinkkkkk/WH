package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class MyCourseFreeVO {

	// 나만의 코스 상세페이지
	int tripUniqueNumber; // 여행코드
	String myCourseCode; // 나만의코스코드...
	String couOrd; // 순서
	int myCouFreeCode; // 나만의코스지역번호
}
