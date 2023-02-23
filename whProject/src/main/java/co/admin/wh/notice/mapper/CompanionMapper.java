package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionMapper {
	
	List<CompanionVO> getCompanionList();//전체조회
	
	CompanionVO detailSelect(CompanionVO cvo);
	
	int companionInsert(CompanionVO vo);
	
	int imgInsert(ImageVO ivo);
	
}
