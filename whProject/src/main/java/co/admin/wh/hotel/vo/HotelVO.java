package co.admin.wh.hotel.vo;

import lombok.Data;

@Data
public class HotelVO {
	public int hotelId; // 호텔아이디
	public String hotelName; // 호텔이름
	public long star; // 별점
	public String hotelAddr;
	public String hotelInfo; // 객실정보
	public String hotelContent; // 상세정보
	public String hotelRegion; // 지역
	public String roomGrade; // 방 등급
	public long roomCount; // 방 개수
	public long roomPrice; // 가격
	public long roomLimit; // 최대수용인원
	public String img1;
	public String img2;
	public String img3;
	public String img4;
	public String img5;
	public HotelVO() {};

   public HotelVO(String hotelName,
		   long star, String hotelAddr, String hotelInfo,
           String hotelContent, String hotelRegion,String roomGrade,
		   long roomCount, long roomPrice, long roomLimit, String img1,
		   String img2,String img3,String img4,String img5){
	  this.hotelName = hotelName;
	  this.star = star;
	  this.hotelAddr = hotelAddr;
	  this.hotelInfo = hotelInfo;
	  this.hotelContent = hotelContent;
	  this.hotelRegion = hotelRegion;
	  this.roomGrade = roomGrade;
	  this.roomCount = roomCount;
	  this.roomPrice = roomPrice;
	  this.roomLimit = roomLimit;  
	  this.img1 = img1;
	  this.img2 = img2;
	  this.img3 = img3;
	  this.img4 = img4;
	  this.img5 = img5;
   }
	
}
