package co.admin.wh.hotel.vo;

import lombok.Data;

@Data
public class HotelVO {
	public int hotelId; // 호텔아이디
	public String hotelName; // 호텔이름
	public long star; // 별점
	public String hotelAddr;
	public String hotelInfo; // 객실정보
	public String imgGroCode;
	public String hotelContent; // 상세정보
	public String hotelRegion; // 지역
	public HotelVO() {};

   public HotelVO(String hotelName,
		   long star, String hotelAddr, String hotelInfo, String imgGroCode,
           String hotelContent, String hotelRegion) {
	  // TODO Auto-generated constructor stub
	  this.hotelName = hotelName;
	  this.star = star;
	  this.hotelAddr = hotelAddr;
	  this.hotelInfo = hotelInfo;
	  this.imgGroCode = imgGroCode;
	  this.hotelContent = hotelContent;
	  this.hotelRegion = hotelRegion;
      
   }
	
}
