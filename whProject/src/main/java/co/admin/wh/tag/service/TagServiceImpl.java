package co.admin.wh.tag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.admin.wh.tag.mapper.TagMapper;
import co.admin.wh.tag.vo.TagVO;
import co.admin.wh.trip.vo.MyCourseVO;

@Service
public class TagServiceImpl implements TagService{
	@Autowired
	private TagMapper map;
	
	@Override
	public List<MyCourseVO> aroundTagList(MyCourseVO tagName) {
		
		
		// 태그별 리스트
		return map.aroundTagList(tagName);
	}

	@Override
	public List<TagVO> tagList() {
		// 태그리스트
		return map.tagList();
	}

	@Override
	public List<String> searchTags(String tag) {
		// 태그 ul 만들기
		return map.searchTags(tag);
	}
}
