package co.admin.wh.notice.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	int noticeCode;    //번호
	String noticeTitle;   //제목 
	String noticeContent;  //내용 
	int noticeHit;         //조회수 
	String noticeFile;    //파일 
	Date noticeRedDate ;   //업로드날짜 
	Date noticeModDate;    //수정날짜 
}
