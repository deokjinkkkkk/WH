package co.admin.wh.notice.vo;

import lombok.Data;

//페이징을 위한 VO
@Data
public class VideoSearchVO extends VideoVO {
	Integer first; 
	Integer last;
}
