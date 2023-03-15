package co.admin.wh.diary.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.diary.vo.FollowVO;

public interface FollowMapper {

	//리스트 
		public List<Map<String,Object>> followList(String id);
		//팔로우 
		public int insertFollow(FollowVO vo);
		
		//언팔 
		public int DelectFollow(FollowVO vo);  
		
		//상세페이지로??
		public Map<String,Object> selectFollow(FollowVO vo);
}
