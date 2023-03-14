package co.admin.wh.diary.service;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.diary.vo.DiaryVO;
import co.admin.wh.notice.vo.FoodSearchVO;

public interface DiaryService {
	
	List<DiaryVO> getDiaryList(); //리스트 
	
	List<DiaryVO> detailDiary(String id);
	int diaryInsert(DiaryVO vo); //등록 
	
	int diaryDetail(DiaryVO vo); //상세페이지 
	
	int diaryUpdate(DiaryVO vo); //수정 
	
	int diaryDelete(DiaryVO vo); //삭제 
	
	
	int imgInsert(ImageVO ivo); //이미지 테이 등록 
	
	DiaryVO imgSelect(DiaryVO vo);  //이미지 조회
	
	 
}
