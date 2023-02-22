package co.admin.wh.notice.service;


import java.util.List;
import java.util.Map;

import co.admin.wh.notice.vo.CompanionVO;
import co.admin.wh.notice.vo.NoticeVO;

public interface CompanionService {
	
	List<CompanionVO> getCompanionList();
	
	int companionInsert(CompanionVO vo);
}
