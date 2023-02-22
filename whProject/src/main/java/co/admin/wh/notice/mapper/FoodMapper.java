package co.admin.wh.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.notice.vo.FoodVO;

public interface FoodMapper {
	
	List<FoodVO> foodList();
	FoodVO foodSelect(FoodVO vo);
	int foodInsert(FoodVO vo);
	int foodUpdate(FoodVO vo);
	int foodDelete(FoodVO vo);
	
	List<FoodVO> foodSearch(@Param("key") String key, @Param("val") String val);
}
