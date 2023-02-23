package co.admin.wh.notice.service;

import java.util.List;

import co.admin.wh.notice.vo.FoodVO;
import co.admin.wh.notice.vo.PagingVO;

public interface FoodService {
	List<FoodVO> getFoodList();
	FoodVO getFoodSelect(FoodVO vo);
	
	
}
