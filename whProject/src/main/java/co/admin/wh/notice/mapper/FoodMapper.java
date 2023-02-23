package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.notice.vo.FoodVO;
import co.admin.wh.notice.vo.PagingVO;

public interface FoodMapper {

	List<FoodVO> getFoodList();
	FoodVO getFoodSelect(FoodVO vo);
	
	
}
