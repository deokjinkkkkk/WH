package co.admin.wh.notice.vo;

import lombok.Data;

@Data
public class FoodSearchVO extends FoodVO {

	Integer first;  //페이징 
	Integer last;
}
