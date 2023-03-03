package co.admin.wh.todo.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class TodoVO {

	private int todoCode;//번호
	
	private String id;//회원아이디
	
	private String todoContent;//내용
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date todoDate;//날짜
	
	private int todoFlag;//구분(1.todolist - 2.완료)
}
