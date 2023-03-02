package co.admin.wh.common.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CommentsVO {
	int comCode;
	String id;
	String comContent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date comDate;
	int comLock;
	String comOrder;
	String comGroup;
	String block;
	String comFlag;
	int comBoardCode;
	
	String name;
}
