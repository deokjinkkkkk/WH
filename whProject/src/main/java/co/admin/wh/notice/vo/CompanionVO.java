package co.admin.wh.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CompanionVO  {
	private int compCode; //동행자 코드
	private String id; //아이디
	
	private String compGroup; //동행자 그룹
	
	private String compTitle; //동행자 게시글 제목
	private String compCount; //인원수
	private String compLocal; //지역
	private String compLocalName; //지역
	
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date compStartDate; //여행 시작 날짜
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date compEndDate; //여행 종료 날짜
	private String compContent; //동행자 게시글 내용
	private String imgGroCode; //이미지 그룹 코드
	
	private String tel; //전화번호
	private String name; //이름
	private String gender; //성별
	
	private String imgPath; //이미지 경로
	private String imgName; //이미지 이름
	
	String comCode; //공통코드
	String comName;//공통코드
	
	String coState; //회원 상태
	int seCode;
	int repCode;
	
	String writer; //작성자
	String login; //로그린
	
	
}
