package co.admin.wh.diary.web;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.admin.wh.diary.mapper.FollowMapper;
import co.admin.wh.diary.service.FollowService;
import co.admin.wh.diary.vo.FollowVO;

@RestController
public class FollowController {
	
	@Autowired FollowService service;
	
	@Autowired FollowMapper mapper;
	
	//팔로워 회원 여부 check
	@GetMapping("/checkFollow/{id}/{followId}")
	public boolean checkFollow(@PathVariable("id") String id, 
								@PathVariable("followId") String followId
								,FollowVO vo)
	{
	    boolean isFollowing = false; 

	    // 팔로우 정보 조회
	    vo.setFollowingId(id);
	    vo.setFollowId(followId);
	  
	    if ( mapper.checkFollow(vo)) {
	        isFollowing = true;
	    }

	    return isFollowing;
	}

	//팔로워 추가
	@PostMapping("/insertFollow/{followingId}")
	public Map<String, Object> insertFollow(@PathVariable("followingId") String followingId,
											FollowVO vo) {

	    vo.setFollowingId(followingId);
	  	    
	    int r = mapper.insertFollow(vo); //1) 빈 map 넘길때 사용 한다  2가지 방식 있다. 
	    return Collections.singletonMap("cnt", r);
	}
	
	//팔로우 취소
	@PostMapping("/unFollow/{followingId}")
	public Map<String, Object> unFollow(@PathVariable("followingId") String followingId,
										FollowVO vo) {

	    vo.setFollowingId(followingId);
	    
	    int r = mapper.unFollow(vo);
	    Map<String, Object> resultMap = new HashMap<>();
	    resultMap.put("cnt", r); //2) 빈 map 넘길때 사용 한다 
	    return resultMap;
	}
	
	//팔로워 카운트 수
	@GetMapping("/followCount/{followId}")
	public Map<String, Object> followCount(@PathVariable("followId") String followId) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("followCount", mapper.followCount(followId));
		map.put("followingCount", mapper.followingCount(followId));
		
		return map;

	}
	
	//팔로워 리스트 출력
	@PostMapping("/follower/{followId}")
	public List<Map<String, Object>> followerList(@PathVariable String followId) {
		return mapper.followerList(followId);
	}
	
	//팔로잉 리스트 출력
	@PostMapping("/following/{followId}")
		public List<Map<String, Object>> followingList(@PathVariable String followId) {
		
		return mapper.followingList(followId);
	}


}
