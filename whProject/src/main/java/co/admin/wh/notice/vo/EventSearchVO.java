package co.admin.wh.notice.vo;

import lombok.Data;

@Data
public class EventSearchVO extends EventVO{
	
	Integer first;  //페이징 
	Integer last;
	
}
