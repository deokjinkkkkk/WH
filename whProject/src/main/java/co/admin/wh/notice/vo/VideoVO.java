package co.admin.wh.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class VideoVO {
	 
	int videoCode;
	String videoName;
	String videoContent;
	int videoHit;
	@JsonFormat(pattern = "yyyy/mm/dd")
	Date videoRegDate;
	@JsonFormat(pattern = "yyyy/mm/dd")
	Date videoModDate;
	String videoRegion;
	String url;
}
