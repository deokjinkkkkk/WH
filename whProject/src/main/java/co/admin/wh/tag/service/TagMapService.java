package co.admin.wh.tag.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
@Service
public interface TagMapService {
	
	//tagCode와 myCourseCode를 매개변수로 받아 tag_map 테이블에 추가
	int saveTagMyCourse(@Param("tagCode")Integer tagCode, @Param("myCourseCode")Integer myCourseCode);
	
	int delTagMyCourse(@Param("myCourseCode") Integer myCourseCode);
}
