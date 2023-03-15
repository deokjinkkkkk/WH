package co.admin.wh.trip.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date couRegDate; //코스등록일자
	Date couModDate; //코스수정일자
	String couLat; //위도
	String couLon; //경도
	String imgGroCode; //이미지그룹번호
	int regionCode; // + 추가, 지역코드
	String contentId; // + 추가, 코스 고유번호(밑에 파생된 여행지에 붙이기)
	String tripSumm; // + 추가, 여행 개요... ㅇㄴ
	int couOrder; // + 추가, 코스 순서
	
	// 여행지 select
	String tripLat; //위도
	String tripLon; //경도
	String tripName; // 여행지 이름	
	
}
