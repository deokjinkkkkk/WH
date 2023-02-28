package co.admin.wh.notice.service;


import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionService {
	
	List<CompanionVO> getCompanionList(CompanionSearchVO cvo);
	
	CompanionVO detailSelect(CompanionVO cvo);
	
	int companionInsert(CompanionVO vo);
	
	int imgInsert(ImageVO ivo);
	
	int getCountTotal(CompanionSearchVO cvo);
	
	int companionUpdate(CompanionVO vo);
	
	int companionDelete(CompanionVO vo);
}
