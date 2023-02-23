package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.vo.FoodVO;



@Service
public class FoodServiceImpl implements FoodService {
@Autowired
private FoodMapper mapper;
	
	
	//목록보기
	@Override
	public List<FoodVO> getFoodList() {
		return mapper.getFoodList();
	}
	
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



}
