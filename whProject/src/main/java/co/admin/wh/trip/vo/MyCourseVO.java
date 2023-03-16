package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class MyCourseVO {
	//나만의 코스 -> 코스 목록, 리스트 화면
	int myCourseCode; //나만의코스 번호
	String theme; //코스테마
	String myCouDate; //코스일정
	String myCouName; //코스명
	String myCouContent; //코스설명
	int myCouState; // 코스 공유 상태값
	String myCouId; // 회원아이디
	
	//tag와 join을 위한
	//String tagName;
	
}
