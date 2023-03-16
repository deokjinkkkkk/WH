package co.admin.wh.tag.service;

import java.util.List;

import co.admin.wh.tag.vo.TagVO;
import co.admin.wh.trip.vo.MyCourseVO;

public interface TagService {
	List<MyCourseVO> aroundTagList(MyCourseVO tagName); //태그별로 리스트 띄우기
	
	List<TagVO> tagList(); // 태그 리스트
	
	List<String> searchTags(String tag);//태그 ul만들기 -> 입력받은 tag 문자열과 일치하는 태그명을 검색하고, 검색된 태그명들을 오름차순으로 정렬하여 반환하는 역할
}
