package co.admin.wh.diary.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import co.admin.wh.diary.mapper.FollowMapper;
import co.admin.wh.diary.vo.FollowVO;
@Service
public class FollowServiceImpl implements FollowService {
	FollowMapper mapper;

	@Override
	public List<Map<String, Object>> followerList(String id) {
		//팔로워 리스트 
		return mapper.followerList(id);
	}



	@Override
	  public boolean checkFollow(FollowVO vo) {
	    return mapper.checkFollow(vo);
	  }




	@Override
	public List<Map<String, Object>> followingList(String id) {
		//팔로잉 리스트
		return mapper.followingList(id);
	}



	@Override
	public int insertFollow(FollowVO vo) {
		// TODO Auto-generated method stub
		return mapper.insertFollow(vo);
	}



	@Override
	public int unFollow(FollowVO vo) {
		// TODO Auto-generated method stub
		return mapper.unFollow(vo);
	}



	@Override
	public int followCount(String id) {
		// TODO Auto-generated method stub
		return mapper.followCount(id);
	}



	@Override
	public int followingCount(String id) {
		// TODO Auto-generated method stub
		return mapper.followingCount(id);
	}



	
	
	


}
