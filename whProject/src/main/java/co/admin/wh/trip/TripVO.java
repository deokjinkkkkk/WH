package co.admin.wh.trip;

import java.sql.Date;

import lombok.Data;

@Data
public class TripVO {
	int tripCode;
	String tripName;
	String tripTel;
	String tripAddr;
	String tripLat;
	String tripLon;
	String TripSumm;
	String TripContent;
	String homepage;
	String tripRegion;
	String imgGroCode;
	Date tripDate;
}
