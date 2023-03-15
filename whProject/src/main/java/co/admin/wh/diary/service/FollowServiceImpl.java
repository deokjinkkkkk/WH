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
	public int insertFollow(FollowVO vo) {
		//팔로우
		return mapper.insertFollow(vo);
	}

	@Override
	public int unFollow(FollowVO vo) {
		//언팔로우 
		return mapper.unFollow(vo);
	}

	@Override
	public boolean checkFollow(FollowVO vo) {
		//파로우 유무
		return checkFollow(vo);
	}

	@Override
	public Map<String, Object> followCount(String id) {
		//팔로우 수 
		return mapper.followCount(id);
	}


	@Override
	public List<Map<String, Object>> followingList(String id) {
		//팔로잉 
		return mapper.followingList(id);
	}

	
	


}
