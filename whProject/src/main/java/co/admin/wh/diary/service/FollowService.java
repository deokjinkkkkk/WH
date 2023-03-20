package co.admin.wh.diary.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import co.admin.wh.diary.vo.FollowVO;

public interface FollowService {
	//팔로워 리스트 
	public List<Map<String,Object>> followerList(String id);
	
	//팔로워 리스트 
	public List<Map<String,Object>> followingList(String id);
	//팔로우 
	public int insertFollow(FollowVO vo);
	
	//언팔 
	public int unFollow(FollowVO vo);  
	
	//팔로우 체크(유무확인)
	boolean checkFollow(FollowVO vo);
	
	//팔로우수 
	public int followCount(String id);
	
	//팔로워수 
	public int followingCount(String id);
}
