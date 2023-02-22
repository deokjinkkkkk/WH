package co.admin.wh.notice.service;

import java.util.List;


import org.springframework.stereotype.Service;
import co.admin.wh.notice.mapper.FoodMapper;
import co.admin.wh.notice.vo.FoodVO;


@Service
public class FoodServiceImpl implements FoodService {

private FoodMapper mapper;
	

	@Override
	public List<FoodVO> getFoodList() {
		return mapper.getFoodList();
	}

	@Override
	public FoodVO getFoodSelect(FoodVO vo) {
		return mapper.getFoodSelect(vo);
	}

}
