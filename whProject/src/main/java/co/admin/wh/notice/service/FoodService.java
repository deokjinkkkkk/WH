package co.admin.wh.notice.service;

import java.util.List;


import co.admin.wh.notice.vo.FoodSearchVO;
import co.admin.wh.notice.vo.FoodVO;

public interface FoodService {
	
	List<FoodVO> getFoodList(FoodSearchVO svo);
	int getCountTotal(FoodSearchVO svo);
	FoodVO detailSelect(FoodVO fvo);
	
	FoodVO getFoodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	
	
	
}
