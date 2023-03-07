package co.admin.wh.hotel.vo;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ReservationVO {
	public int resId;
	public int childCount;
	public int humanCount;
	public String resName;
	public String resTel;
	public String resEmail;
	public String resRequest;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public Date checkIn;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public Date checkOut;
	public String id;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public Date payDate;
	public int totalPay;
	public int payState;
	public int hotelId;
	

}
