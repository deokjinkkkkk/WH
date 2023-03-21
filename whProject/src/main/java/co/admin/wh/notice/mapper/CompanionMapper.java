package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;

public interface CompanionMapper {
	
	List<CompanionVO> getCompanionList(CompanionSearchVO cvo);//동행자 목록 가져오기
	
	List<CompanionVO> comSelList(CompanionVO vo); //내 동행자 신청 목록 가져오기
	
	List<CompanionVO> comSelMyList(CompanionVO vo);// 내가 신청한 동행자 목록 가져오기
	 
	List<CompanionVO> test(CompanionVO vo); 
	
	List<CompanionVO> selTitle(CompanionVO vo);
	
	int  comBtn(CompanionVO vo); 
	
	int  selBtn(CompanionVO vo);
	
	CompanionVO detailSelect(CompanionVO cvo); //동행자 상세페이지
	
	CompanionVO localSelect(CompanionVO cvo);
	
	CompanionVO imgSelect(CompanionVO cvo); //이비지 가져오기
	
	int companionInsert(CompanionVO vo); //동행자 등록
	
	int imgInsert(ImageVO ivo); //이미지 삽입

	int getCountTotal(CompanionSearchVO cvo); //페이징 관리
	
	int companionUpdate(CompanionVO vo); //동행자 업데이트
	
	int companionDelete(CompanionVO vo); //동행자 업데이트 삭제
	
	int imageSelect(ImageVO ivo); //이미지 가져오기
	
	int comListInsert(CompanionVO vo); //동행자 신청자 등록
	
	int approve(CompanionVO vo); //동행자 승인
	
	int reject(CompanionVO vo); //동행자 거절
	
	int comSelDelete(CompanionVO vo); //동행자 신청 삭제
	
}
