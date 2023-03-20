package co.admin.wh.hotel.vo;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CancelVO extends ReservationVO{ // join해서 select해야해서 상속받음
	public int resId;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	public Date applyDate; // 환불승인일자
	public int returnPay; // 환불금액
	public Date submitDate; // 환불신청일자
	
	//관리자 예약검색 시 필요
	public String option; // 관리자 예약검색 - 옵션
	public String content; // 검색옵션 선택 후 검색어 입력
}