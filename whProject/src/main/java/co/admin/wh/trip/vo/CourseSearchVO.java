package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class CourseSearchVO extends CourseVO {

	Integer first; //페이징
	Integer last;
}
