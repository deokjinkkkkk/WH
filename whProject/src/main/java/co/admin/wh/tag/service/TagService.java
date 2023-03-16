package co.admin.wh.tag.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import co.admin.wh.tag.vo.TagVO;

public interface TagService {
	
	
	TagVO findTagBytag(@Param("tagName")String tagName); //tagName에서 태그를 찾아서 tag 객체로 반환
	
	int saveTag(@Param("tagName")String tagName);//새로운 태그를 db에 추가하고 해당 태그의 tagCode에 반환
	
	int addCntTag(@Param("tagCode")Integer tagCode);//tagCode에 해당하는 태그의 사용 횟수를 1씩 증가
		
	List<TagVO> findByTagCnt();

}
