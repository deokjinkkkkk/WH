package co.admin.wh.notice.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class NoticeVO {
	int noticeCode;
	String noticeTitle;
	String noticeContent;
	int noticeHit;
	String noticeFile;
	Date noticeRedDate ;
	Date noticeModDate;
}
