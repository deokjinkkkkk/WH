package co.admin.wh.common.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReportVO {
	int repCode;
	String id;
	String targetId;
	@JsonFormat(pattern = "yyyy/mm/dd")
	Date repDate;
	String repReason;
	String repCatCode;
	
	String comName;
	int compCode;
	
	int state;
	
}
