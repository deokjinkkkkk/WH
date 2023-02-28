package co.admin.wh.trip.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class CourseVO {
	// 코스
	String couCode; //코스번호
	int tripCode; //여행자번호
	String couName; //코스명
	String couDist; //코스길이
	String sido; //행정구역
	int time; //총소요시간
	String couContent; //코스설명
	Date couRegDate; //코스등록일자
	Date couModDate; //코스수정일자
	String couLat; //위도
	String couLon; //경도
	String imgGroCode; //이미지그룹번호
	
}