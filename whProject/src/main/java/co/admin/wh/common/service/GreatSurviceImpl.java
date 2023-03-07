package co.admin.wh.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.GreatMapper;
import co.admin.wh.common.vo.GreatVO;

@Service
public class GreatSurviceImpl implements GreatService {
	@Autowired GreatMapper mapper;

	@Override
	public boolean greatCheck(GreatVO vo) {
		return mapper.greatCheck(vo);
	}

	@Override
	public int greatUP(GreatVO vo) {
		return mapper.greatUP(vo);
	}

	@Override
	public int greatDown(GreatVO vo) {
		return mapper.greatDown(vo);
	}

	@Override
	public int greatTotal(int greatNcode) {
		return greatTotal(greatNcode);
	}

	
	

	

	

	
	
	



}
