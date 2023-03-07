package co.admin.wh.common.vo;



import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommentsVO {
	int comCode;
	String id;
	String comContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
	Date comDate;
	int comLock;
	int comOrder;
	String comGroup;
	String block;
	String comFlag;
	int comBoardCode;
	
	String name;
}
