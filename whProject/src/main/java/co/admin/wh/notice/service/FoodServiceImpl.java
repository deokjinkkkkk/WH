package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;



@Service
public class FoodServiceImpl implements FoodService {
@Autowired
private FoodMapper mapper;
	
	

/*
 * //조회
 * 
 * @Override public FoodVO getFoodSelect(FoodVO vo) { return
 * mapper.getFoodSelect(vo); }
 */
	//게시글 등
	@Override
	public int foodInsert(FoodVO vo) {
		return mapper.foodInsert(vo);
	}

	//전체보기,페이징
	@Override
	public List<FoodVO> getFoodList(FoodSearchVO svo) {
		return mapper.getFoodList(svo);
	}

	@Override
	public int getCountTotal(FoodSearchVO svo) {
		// 페이지 수 관리
		return mapper.getCountTotal(svo);
	}

	@Override
	public FoodVO detailSelect(FoodVO fvo) {
		// 상세보기
		return mapper.detailSelect(fvo);
	}

	@Override
	public int imgInsert(ImageVO ivo) {
		//이미지 조
		return mapper.imgInsert(ivo);
	}

	@Override
	public int hitUpdate(int foodCode) {
		// 조회수증가
		return mapper.hitUpdate(foodCode);
	}

	@Override
	public int foodDelete(FoodVO vo) {
		// 게시글 삭제
		return mapper.foodDelete(vo);
	}

	@Override
	public int foodUpdate(FoodVO vo) {
		// 수정
		return mapper.foodUpdate(vo);
	}

	@Override
	public FoodVO imgSelect(FoodVO vo) {
		return mapper.imgSelect(vo);
	}


	


}
