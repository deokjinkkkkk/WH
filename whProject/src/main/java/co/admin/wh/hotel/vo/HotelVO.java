package co.admin.wh.hotel.vo;

import lombok.Data;

@Data
public class HotelVO {
	
	public int hotelId; // 호텔아이디
	public String hotelName; // 호텔이름
	public String star; // 별점
	public String hotelAddr;
	public String hotelInfo; // 객실정보
	public String imgGroCode;
	public int hotelFlag; // 구분
	public String hotelContent; // 상세정보
	public String hotelPrice; // 호텔가격
}
