package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;


public interface FoodMapper {

	List<FoodVO> getFoodList(FoodVO vo);
	int getCountTotal(FoodSearchVO svo);
	FoodVO detailSelect(FoodVO fvo);
	
	FoodVO getFoodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	
	
}
