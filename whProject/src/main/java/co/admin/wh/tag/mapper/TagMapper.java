package co.admin.wh.tag.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.tag.vo.TagVO;

public interface TagMapper {

	
	int saveTag(TagVO vo);//새로운 태그를 db에 추가하고 해당 태그의 tagCode에 반환
	

	List<TagVO> findByTagCnt(); //태그 사용량을 기준으로 상위 10개 태그를 찾는
	
	//tagCode와 myCourseCode를 매개변수로 받아 tag_map 테이블에 추가
	int saveTagMyCourse(@Param("tagName") String tagName, @Param("myCourseCode")Integer myCourseCode);
	
	int delTagMyCourse(@Param("myCourseCode") Integer myCourseCode);
}