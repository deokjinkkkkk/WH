package co.admin.wh.common.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReportVO {
	int repCode;
	String id;
	String target_id;
	@JsonFormat(pattern = "yyyy/mm/dd")
	Date repDate;
	int repReason;
	String repCatCode;
}
