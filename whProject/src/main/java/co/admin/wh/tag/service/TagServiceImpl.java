package co.admin.wh.tag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.admin.wh.tag.mapper.TagMapper;
import co.admin.wh.tag.vo.TagVO;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagMapper map;

	@Override
	public Boolean saveTag(String tags, int myCourseCode) {

		// tags = 새로 저장하려는 tag_name을(" ")공백으로 구분하여 하나의 문자열로 합친 것
		String[] taglist = tags.split(" "); // split로 공백 잘라서 taglist에 넣기

		TagVO vo = new TagVO();
		vo.setMyCourseCode(myCourseCode);

		// 기존 tag_map 지우고
		map.delTagMyCourse(myCourseCode);

		for (int i = 0; i < taglist.length; i++) {
			System.out.println("=============" + taglist[i]); // taglist = tagName
			// tag table 정리
			if (taglist[i].startsWith("#")) { // #으로 시작하는 taglist만 저장하도록 하는 if문
				vo.setTagName(taglist[i]);
				map.saveTag(vo);

				// tag_map 추가
				map.saveTagMyCourse(taglist[i], myCourseCode);
			}

		}
		return true;
	}
}
