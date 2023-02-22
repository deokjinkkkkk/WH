package co.admin.wh.notice.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionMapper {
	
	List<CompanionVO> getCompanionList();
	
	int companionInsert(CompanionVO vo);

}
