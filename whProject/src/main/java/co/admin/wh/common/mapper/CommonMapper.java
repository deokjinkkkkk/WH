package co.admin.wh.common.mapper;

import java.util.List;

import co.admin.wh.common.vo.CommonVO;

public interface CommonMapper {
	
	List<CommonVO> commonLocal();
	
	List<CommonVO> commonGroup();
	
	List<CommonVO> commonReport();
	
	List<CommonVO> commonState();
}
