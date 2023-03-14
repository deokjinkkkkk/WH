package co.admin.wh.hotel.vo;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data // 지금 캔슬테이블 안쓰고있는데 혹시몰라서 만들어놨슴다
public class CancelVO extends HotelVO{ // join해서 select해야해서 상속받음
	public int resId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public Date cancelDate; // 취소일자
	public int returnPay; // 환불금액
}