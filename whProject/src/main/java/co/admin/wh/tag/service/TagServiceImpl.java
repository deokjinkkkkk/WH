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
	public int saveTag(String tagName) {
		// TODO Auto-generated method stub
		return map.saveTag(tagName);
	}

	@Override
	public int addCntTag(int tagCode) {
		// TODO Auto-generated method stub
		return map.addCntTag(tagCode);
	}

	@Override
	public List<TagVO> findByTagCnt() {
		// TODO Auto-generated method stub
		return map.findByTagCnt();
	}

	@Override
	public TagVO findTagBytag(String tagName) {
		
		return map.findTagBytag(tagName);
	}
	
}
