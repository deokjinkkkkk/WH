package co.admin.wh.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.mapper.GreatMapper;
import co.admin.wh.common.vo.GreatVO;
@Service
public class GreatSurviceImpl implements GreatService {
	@Autowired GreatMapper mapper;
	
	@Override
	public List<Map<String, Object>> myGreatList() {
		//마이페이지 좋아요 리스트 출력 
		return mapper.myGreatList();
	}

	@Override
	public int greatDelete(GreatVO vo) {
		//마이페이지 좋아요 삭제 
		return mapper.greatDelete(vo);
	}


}
