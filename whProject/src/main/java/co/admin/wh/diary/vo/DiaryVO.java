package co.admin.wh.diary.vo;



import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class DiaryVO {
	public int diaryCode;  //리뷰번호 
	public String diaryTitle;   //리뷰제목 
	public String id;    //회원아이디
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date diaryStartDate;  //여행시작 
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date diaryEndDate;  // 여행종료 
	public String diaryContent;  //내용 
	public String imgGroCode; // 첨부파일 
	
	String name; //회원이름 
	private String imgPath;
	private String imgName;
}
