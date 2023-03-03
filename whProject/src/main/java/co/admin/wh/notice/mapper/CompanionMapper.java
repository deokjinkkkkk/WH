package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionMapper {
	
	List<CompanionVO> getCompanionList(CompanionSearchVO cvo);//전체조회
	
	CompanionVO detailSelect(CompanionVO cvo);
	
	CompanionVO imgSelect(CompanionVO cvo);
	
	int companionInsert(CompanionVO vo);
	
	int imgInsert(ImageVO ivo);

	int getCountTotal(CompanionSearchVO cvo);
	
	int companionUpdate(CompanionVO vo);
	
	int companionDelete(CompanionVO vo);
	
	int imageSelect(ImageVO ivo);
	
}
