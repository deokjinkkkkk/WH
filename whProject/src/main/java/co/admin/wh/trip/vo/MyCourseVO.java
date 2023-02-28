package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class MyCourseVO {
	//나만의 코스
	String myCourseCode; //나만의코스 번호
	int tripCode; //여행자아이디
	String theme; //코스테마
	String myCouDate; //코스일정
	String myCouName; //코스명
	String myCouContent; //코스설명
	int myCouState; //소개여부
	
}
