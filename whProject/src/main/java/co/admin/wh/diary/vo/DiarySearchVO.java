package co.admin.wh.diary.vo;

import lombok.Data;

@Data
public class DiarySearchVO extends DiaryVO{

	Integer first;  //페이징 
	Integer last;
}
