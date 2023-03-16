package co.admin.wh.tag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.tag.vo.TagVO;

public interface TagMapper {

	
	TagVO findTagBytag(@Param("tagName")String tagName); //tagName에서 태그를 찾아서 tag 객체로 반환
	
	int saveTag(@Param("tagName")String tagName);//새로운 태그를 db에 추가하고 해당 태그의 tagCode에 반환
	
	int addCntTag(@Param("tagCode")Integer tagCode);//tagCode에 해당하는 태그의 사용 횟수를 1씩 증가
		
	List<TagVO> findByTagCnt(); //태그 사용량을 기준으로 상위 10개 태그를 찾는
}