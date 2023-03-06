package co.admin.wh.common.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.CommonMapper;
import co.admin.wh.common.vo.CommonVO;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonMapper commomMapper;

	@Override
	public List<CommonVO> commonLocal() {
		// TODO Auto-generated method stub
		return commomMapper.commonLocal();
	}

	@Override
	public List<CommonVO> commonGroup() {
		// TODO Auto-generated method stub
		return commomMapper.commonGroup();
	}

	@Override
	public List<CommonVO> commonReport() {
		// TODO Auto-generated method stub
		return commomMapper.commonReport();
	}



}
