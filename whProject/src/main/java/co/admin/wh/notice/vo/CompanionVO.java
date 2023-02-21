package co.admin.wh.notice.vo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CompanionVO {
	private int compCode;
	private String id;
	private String age;
	private String compGroup;
	private String compTitle;
	private int compCount;
	private String compLocal;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date compStartDate;
	private Date compEndDate;
	private String compContent;
	private String imgGroCode;
	private int couCode;
	private String tel;
	private String name;
	private String gender;
	
}
