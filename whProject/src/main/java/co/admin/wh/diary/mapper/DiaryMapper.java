package co.admin.wh.diary.mapper;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.diary.vo.DiaryVO;

public interface DiaryMapper {


	List<DiaryVO> getDiaryList(); //리스트 
	
	int diaryInsert(DiaryVO vo); //등록 
	
	DiaryVO diaryDetail(DiaryVO vo); //상세페이지 
	
	int diaryUpdate(DiaryVO vo); //수정 
	
	int diaryDelete(DiaryVO vo); //삭제 
	
	
	int imgInsert(ImageVO ivo); //이미지 테이 등록 
	
	DiaryVO imgSelect(DiaryVO vo);  //이미지 조회
}
