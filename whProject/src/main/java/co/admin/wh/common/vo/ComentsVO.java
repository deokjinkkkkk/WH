package co.admin.wh.common.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ComentsVO {
	int comCode;
	String id;
	String comContent;
	@JsonFormat(pattern = "yyyy-MM-dd")
	Date comDate;
	int comLock;
	String comOrder;
	String comGroup;
	String block;
	int comFlag;
}
