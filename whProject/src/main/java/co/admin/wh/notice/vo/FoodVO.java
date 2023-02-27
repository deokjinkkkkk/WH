package co.admin.wh.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class FoodVO {
	private int foodCode; //맛집 번호
	private String id;	//회원 아이디
	private String foodTitle;	//제목
	private String foodName;	//상호명
	private String foodAddr;	//주소	
	private String foodContent;	//내용
	private int foodHit;	//조회수	
	private String foodFile;	//첨부파일
	@JsonFormat(pattern="yyyy-MM-dd")	
	private Date foodDate;	//등록일자
	private String imgGroCode;	//이미지그룹번호
	
	
	
}
