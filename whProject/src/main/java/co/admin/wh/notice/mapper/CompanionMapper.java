package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionMapper {
	
	List<CompanionVO> getCompanionList(CompanionSearchVO cvo);//전체조회
	
	List<CompanionVO> comSelList(CompanionVO vo);
	
	List<CompanionVO> comSelMyList(CompanionVO vo);
	
	List<CompanionVO> test(CompanionVO vo);
	
	List<CompanionVO> selTitle(CompanionVO vo);
	
	int  comBtn(CompanionVO vo);
	
	int  selBtn(CompanionVO vo);
	
	CompanionVO detailSelect(CompanionVO cvo);
	
	CompanionVO localSelect(CompanionVO cvo);
	
	CompanionVO imgSelect(CompanionVO cvo);
	
	int companionInsert(CompanionVO vo);
	
	int imgInsert(ImageVO ivo);

	int getCountTotal(CompanionSearchVO cvo);
	
	int companionUpdate(CompanionVO vo);
	
	int companionDelete(CompanionVO vo);
	
	int imageSelect(ImageVO ivo);
	
	int comListInsert(CompanionVO vo);
	
	int approve(CompanionVO vo);
	
	int reject(CompanionVO vo);
	
	int comSelDelete(CompanionVO vo);
	
}
