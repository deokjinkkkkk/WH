package co.admin.wh.notice.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FoodVO {
	int foodCode;
	String id;
	String foodTitle;
	String foodName;
	String foodAddr;
	String foodContent;
	int foodHit;
	String foodFile;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	String foodDate;
	
}
