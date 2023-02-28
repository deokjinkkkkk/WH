package co.admin.wh.notice.vo;



import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NoticeVO {
	int noticeCode;    //번호
	String noticeTitle;   //제목 
	String noticeContent;  //내용 
	int noticeHit;         //조회수 
	String imgGroCode;    //파일 
	@JsonFormat(pattern = "yyyy/MM/dd")
	Date noticeRegDate; //등록날짜
	@JsonFormat(pattern = "yyyy/MM/dd")
	Date noticeModDate;    //수정날짜 
	int noticeCount; //첨부파일 수량


}
