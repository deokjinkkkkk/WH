package co.admin.wh.common.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class TourVO {
	int tourId;
	String tourTitle;
	String tourAddr;
	String tourLat;
	String tourLon;
	String tourSumm;
	String tourContent;
	String tourRegion;
	String imgGroCode;
	String tourTel;
	String restDate;
	String useTime;
	String tourParking;
	String tourChild;
	String tourPet;
	String tourChkCard;
	String tourState;
	String id;
	Date tourDate;
	
	private String imgPath;
	private String imgName;
	
}
