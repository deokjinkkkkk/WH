package co.admin.wh.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class EventVO {
	private int eventCode;//게시글번호
	private String eventTitle;//제목
	private String eventContent;//내용
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date eventStartDate; //이벤트 시작일자
	private String imgGroCode; //이미지 그룹번호
	private String eventYn;//쿠폰발급여부
	@JsonFormat(pattern="yyyy-MM-dd") 
	private Date eventEndDate; //이벤트 종료일자
	private int eventHit; //조회수
	
	//img join을 위한
	private String imgPath;
	private String imgName;
	
}
