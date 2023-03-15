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
	public List<Map<String, Object>> followList(String id) {
		//팔로우 리스트 조회 
		return mapper.followList(id);
	}

	@Override
	public int insertFollow(FollowVO vo) {
		//팔로우 신청 
		return mapper.insertFollow(vo);
	}

	@Override
	public int DelectFollow(FollowVO vo) {
		//언팔
		return mapper.DelectFollow(vo);
	}

	@Override
	public Map<String, Object> selectFollow(FollowVO vo) {
		//목록에서 회원의 게시물로이동?
		return mapper.selectFollow(vo);
	}
	


}
