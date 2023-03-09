package co.admin.wh.common.service;

import java.util.List;

import co.admin.wh.common.vo.CommonVO;

public interface CommonService {
	
	List<CommonVO> commonLocal();
	
	List<CommonVO> commonGroup();
	
	List<CommonVO> commonReport();
	
	List<CommonVO> commonState();
}
