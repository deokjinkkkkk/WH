package co.admin.wh.trip;

import java.sql.Date;

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
	String TripSumm; //개요
	String TripContent; //상세정보
	String homepage; //홈페이지
	String tripRegion; //지역
	String imgGroCode; //이미지그룹번호
	Date tripDate; //등록일자
}
