package co.admin.wh.notice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.vo.CompanionVO;

@Service
public class CompanionServiceImpl implements CompanionService {
	@Autowired
	private CompanionMapper map;
	
	@Override
	public int companionInsert(CompanionVO vo) {
		return map.companionInsert(vo);
	}

}
