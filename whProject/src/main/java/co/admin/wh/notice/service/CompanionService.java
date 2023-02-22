package co.admin.wh.notice.service;


import java.util.List;
import java.util.Map;

import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionService {
	
	List<Map> memberList();
	
	int companionInsert(CompanionVO vo);
}
