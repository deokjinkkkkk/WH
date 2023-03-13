package co.admin.wh.common.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class SanctionsVO {
	int sanCode;
	String id;
	int sanDay;
	Date sanDate;
}
