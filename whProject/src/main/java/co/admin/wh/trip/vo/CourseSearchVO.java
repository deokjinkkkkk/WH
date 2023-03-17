package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class CourseSearchVO extends CourseVO {

	Integer first; //페이징
	Integer last;
	
	int myCouState; // 코스 공유 상태값

}
