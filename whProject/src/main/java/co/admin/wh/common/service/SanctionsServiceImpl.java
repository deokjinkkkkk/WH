package co.admin.wh.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.SanctionsMapper;
import co.admin.wh.common.vo.SanctionsVO;

@Service
public class SanctionsServiceImpl implements SanctionsService{
	@Autowired
	SanctionsMapper sanctionsMapper;

	@Override
	public int sanctionsInsert(SanctionsVO vo) {
		// TODO Auto-generated method stub
		return sanctionsMapper.sanctionsInsert(vo);
	}
	
}
