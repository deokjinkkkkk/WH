package co.admin.wh.hotel.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class HotelVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int hotelId; // 호텔아이디
	public String hotelName; // 호텔이름
	public String star; // 별점
	public String hotelAddr;
	public String hotelInfo; // 객실정보
	public String imgGroCode;
	public int hotelFlag; // 구분
	public String hotelContent; // 상세정보
	public int hotelPrice; // 호텔가격
	public HotelVO() {};

	   public HotelVO(String hotelName,
               String star, String hotelAddr, String hotelInfo, String imgGroCode,
               int hotelFlag, String hotelContent, int hotelPrice) {
      // TODO Auto-generated constructor stub
      this.hotelName = hotelName;
      this.star = star;
      this.hotelAddr = hotelAddr;
      this.hotelInfo = hotelInfo;
      this.imgGroCode = imgGroCode;
      this.hotelFlag = hotelFlag;
      this.hotelContent = hotelContent;
      this.hotelPrice = hotelPrice;
      
   }
	
}
