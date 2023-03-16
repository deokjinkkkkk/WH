package co.admin.wh.tag.vo;

import lombok.Data;

@Data
public class TagVO {
	
		///태그 인덱스
		 int tagCode;
			
		//태그내용 (unique)
		 String tagName;
		 
		 //태그 언급 수
		 int TagCnt;
}
