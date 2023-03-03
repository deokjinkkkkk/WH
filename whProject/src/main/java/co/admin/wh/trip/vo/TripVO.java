package co.admin.wh.trip.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TripVO { 
	// 여행지
	int tripCode; //여행번호
	String tripName; //명칭
	String tripTel; //연락처
	String tripAddr; //주소
	String tripLat; //위도
	String tripLon; //경도
	String tripSumm; //개요
	String tripContent; //상세정보
	String homepage; //홈페이지
	String tripRegion; //지역
	String imgGroCode; //이미지그룹번호
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date tripDate; //등록일자(수정일자로 일단 변경)
	
}
