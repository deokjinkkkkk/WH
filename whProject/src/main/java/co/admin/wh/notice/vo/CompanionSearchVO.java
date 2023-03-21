package co.admin.wh.notice.vo;

import lombok.Data;
//페이징을 위한 VO
@Data
public class CompanionSearchVO extends CompanionVO {
	Integer first;
	Integer last;
}
