package co.admin.wh.notice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.CompanionMapper;
import co.admin.wh.notice.vo.CompanionSearchVO;
import co.admin.wh.notice.vo.CompanionVO;

@Service
public class CompanionServiceImpl implements CompanionService {
	@Autowired
	private CompanionMapper map;
	
	@Override
	public int companionInsert(CompanionVO vo) {
		return map.companionInsert(vo);
	}

	@Override
	public List<CompanionVO> getCompanionList(CompanionSearchVO cvo) {
		// TODO Auto-generated method stub
		return map.getCompanionList(cvo);
	}

	@Override
	public int imgInsert(ImageVO ivo) {
		// TODO Auto-generated method stub
		return map.imgInsert(ivo);
	}

	@Override
	public CompanionVO detailSelect(CompanionVO cvo) {
		// TODO Auto-generated method stub
		return map.detailSelect(cvo);
	}
	
	@Override
	public int getCountTotal(CompanionSearchVO cvo) {
		// 페이지 수 관리
		return map.getCountTotal(cvo);
	}

}
