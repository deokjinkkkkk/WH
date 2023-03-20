package co.admin.wh.hotel.vo;

import lombok.Data;

@Data
public class HotelVO{
	public int hotelId; // 호텔아이디
	public String hotelName; // 호텔이름
	public double star; // 별점
	public String hotelAddr;
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
		   double star, String hotelAddr, String hotelRegion,String roomGrade,
		   long roomPrice, long roomLimit, String img1,
		   String img2,String img3,String img4,String img5){
	  this.hotelName = hotelName;
	  this.star = star;
	  this.hotelAddr = hotelAddr;
	  this.hotelRegion = hotelRegion;
	  this.roomGrade = roomGrade;
	  this.roomPrice = roomPrice;
	  this.roomLimit = roomLimit;  
	  this.img1 = img1;
	  this.img2 = img2;
	  this.img3 = img3;
	  this.img4 = img4;
	  this.img5 = img5;
   }
	
	//예약 가능한 호텔 검색 시 필요
	public int priceRangeMin; // 최저금액
	public int priceRangeMax; // 최고금액
	
}
