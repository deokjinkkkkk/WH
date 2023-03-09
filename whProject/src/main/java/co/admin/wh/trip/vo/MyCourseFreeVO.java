package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class MyCourseFreeVO {

	// 나만의 코스 상세페이지
	int tripUniqueNumber; // 여행고유번호
	String myCourseCode; // 유저 아이디
	String couOrd; // 순서
}
