package co.admin.wh.trip.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TripVO { 
	// 여행지
	int tripCode; // 그냥 숫자
	String tripName; //명칭
	String tripTel; //연락처
	String tripAddr; //주소
	String tripLat; //위도
	String tripLon; //경도
	String tripSumm; //개요
	String tripContent; //상세정보
	String homepage; //홈페이지
	String tripRegion; //지역코드
	String imgGroCode; //이미지그룹번호
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date tripDate; //등록일자(수정일자로 일단 변경)
	String couCode; // + 추가, 코스고유번호
	int couOrder; // + 추가, 코스 순서
	int tripUniqueNumber; // 추가, 여행고유번호
	
	
	String tripAnnounce; // 문의 및 안내
	String restDate; // 쉬는 날
	String useTime; // 이용시간
	String tripParking; // 주차시설
	String tripChild; // 유모차 대여여부
	String tripPet; // 애완동물 동반가능 여부
	String tripChkCard; // 신용카드 가능 여부
	
	

	
}
