package co.admin.wh.diary.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.diary.vo.FollowVO;

public interface FollowMapper {
	//팔로워 리스트 
		public List<Map<String,Object>> followerList(String id);
		
		//팔로워 리스트 
		public List<Map<String,Object>> followingList(String id);
		//팔로우 
		public FollowVO insertFollow(FollowVO vo);
		
		//언팔 
		public FollowVO unFollow(FollowVO vo);  
		
		//팔로우 체크(유무확인)
		boolean checkFollow(FollowVO vo);
		
		
		//팔로우수 
		public Map<String,Object> followCount(String id);
}
