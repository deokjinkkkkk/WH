package co.admin.wh.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date compStartDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date compEndDate;
	private String compContent;
	private String imgGroCode;
	private String couCode;
	
	private String tel;
	private String name;
	private String gender;
	
}
