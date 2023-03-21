package co.admin.wh.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class VideoVO {
	 
	int videoCode; //영상코드
	String videoName; //영상 제목
	String videoContent; //영상 설명
	int videoHit;
	@JsonFormat(pattern = "yyyy/mm/dd")
	Date videoRegDate; //영상 등록 날짜
	String videoRegion; //영상 지역
	String url; //영상 유튜브 주소
	String thumbnail; //영상 유튜브 썸네일
	
}
