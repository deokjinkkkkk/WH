package co.admin.wh.tag.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.admin.wh.tag.mapper.TagMapper;
import co.admin.wh.tag.vo.TagVO;



@Service

public class TagServiceImpl implements TagService{
	
	@Autowired
	private TagMapper map;

	
	@Override
	public Boolean saveTag(String tags, int myCourseCode) {
		
		String[] taglist = tags.split(" ");
		TagVO vo = new TagVO();
		vo.setMyCourseCode(myCourseCode);
		
		//기존 tag_map 지우고 
		map.delTagMyCourse(myCourseCode);
		
		for(int i=0; i<taglist.length; i++) {
			System.out.println("============="+taglist[i]); //taglist = tagName
			// tag table 정리
			vo.setTagName(taglist[i]);
			map.saveTag(vo);
			
			//tag_map 추가
			map.saveTagMyCourse(taglist[i],myCourseCode);
		}
		return true;
	}
}
