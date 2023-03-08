package co.admin.wh.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CompanionVO  {
	private int compCode;
	private String id;
	private String age;
	
	private String compGroup;
	
	private String compTitle;
	private String compCount;
	private String compLocal;
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date compStartDate;
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date compEndDate;
	private String compContent;
	private String imgGroCode;
	private String couCode;
	
	private String tel;
	private String name;
	private String gender;
	
	private String imgPath;
	private String imgName;
	
	String comCode;
	String comName;
	
	int state;
}
