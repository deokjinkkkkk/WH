package co.admin.wh.common.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.common.vo.GreatVO;

public interface GreatMapper {

	
	// 마이페이지 좋아요 리스트 출력

	public List<Map<String, Object>> myGreatList();
	// 마이페이지 여행 좋아요 리스트 출력

	// 마이페이지 호텔 좋아요 리스트 출력

	// 마이페이지 맛집 좋아요 리스트 출력

	// 마이페이지 커뮤니트 좋아요 리스트 출력

	// 마이페이지 좋아요 삭제
	public int greatDelete(GreatVO vo);
}
