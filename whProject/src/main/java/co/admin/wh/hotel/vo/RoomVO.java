package co.admin.wh.hotel.vo;

import lombok.Data;

@Data
public class RoomVO {

	public int hotelId; // 호텔 id
	public String hotelName;
	public String roomGrade; // 
	public long roomCount;
	public String roomRegion;
	public long roomPrice;
	public String imgGroCode;
	public long roomLimit;
	public RoomVO() {};

   public RoomVO(String hotelName, String roomGrade,
		   long roomCount, String roomRegion,
		   long roomPrice, String imgGroCode, long roomLimit) {
	  // TODO Auto-generated constructor stub
	  this.hotelName = hotelName;
	  this.roomGrade = roomGrade;
	  this.roomCount = roomCount;
	  this.roomRegion = roomRegion;
	  this.roomPrice = roomPrice;
	  this.imgGroCode = imgGroCode;
	  this.roomLimit = roomLimit;
      
   }
	
}
