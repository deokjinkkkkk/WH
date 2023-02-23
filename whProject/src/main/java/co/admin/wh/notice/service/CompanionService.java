package co.admin.wh.notice.service;


import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionService {
	
	List<CompanionVO> getCompanionList();
	CompanionVO detailSelect(CompanionVO cvo);
	
	int companionInsert(CompanionVO vo);
	

	int imgInsert(ImageVO ivo);
	
}
