package co.admin.wh.trip.vo;

import lombok.Data;

@Data
public class TripSearchVO extends TripVO {

	Integer first; //페이징
	Integer last;
}
