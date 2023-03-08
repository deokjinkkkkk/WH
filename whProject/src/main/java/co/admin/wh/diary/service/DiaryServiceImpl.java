package co.admin.wh.diary.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.diary.mapper.DiaryMapper;
import co.admin.wh.diary.vo.DiaryVO;

@Service
public class DiaryServiceImpl implements DiaryService {
	DiaryMapper mapper;
	
	
	@Override
	public int diaryInsert(DiaryVO vo) {
		//등록 
		return mapper.diaryInsert(vo);
	}

	@Override
	public DiaryVO diaryDetail(DiaryVO vo) {
		// 상세페이지 
		return mapper.diaryDetail(vo);
	}

	@Override
	public int diaryUpdate(DiaryVO vo) {
		//수정 
		return mapper.diaryUpdate(vo);
	}

	@Override
	public int diaryDelete(DiaryVO vo) {
		//삭제 
		return mapper.diaryDelete(vo);
	}

	@Override
	public int imgInsert(ImageVO ivo) {
		// 이미지 등록 
		return mapper.imgInsert(ivo);
	}

	@Override
	public DiaryVO imgSelect(DiaryVO vo) {
		// 이미지 조회 
		return mapper.imgSelect(vo);
	}

	@Override
	public List<DiaryVO> getDiaryList() {
		//리스트 조회 
		return mapper.getDiaryList();
	}

}
