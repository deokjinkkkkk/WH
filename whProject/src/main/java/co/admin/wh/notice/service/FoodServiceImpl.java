package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;



@Service
public class FoodServiceImpl implements FoodService {
@Autowired
private FoodMapper mapper;
	
	

	//상세페이지
	@Override
	public FoodVO getFoodSelect(FoodVO vo) {
		return mapper.getFoodSelect(vo);
	}
	
	//글작성
	@Override
	public int foodInsert(FoodVO vo) {
		return mapper.foodInsert(vo);
	}

	//목록보기
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



}
