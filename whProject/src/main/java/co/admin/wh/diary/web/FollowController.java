package co.admin.wh.diary.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.admin.wh.diary.mapper.FollowMapper;
import co.admin.wh.diary.service.FollowService;
import co.admin.wh.diary.vo.FollowVO;
import co.admin.wh.member.vo.MemberVO;

@RestController
public class FollowController {
	
	@Autowired FollowService service;
	
	@Autowired FollowMapper mapper;
	
	//회원정보체크 하기
	@GetMapping("/checkFollow/{id}/{followId}")
	public boolean checkFollow(@PathVariable("id") String id, FollowVO vo, MemberVO mvo, Principal principal, @PathVariable("followId") String followId) {
	    boolean isFollowing = false; 
	    // 로그인한 사용자 정보 가져오기
	    mvo.setId(principal.getName());

	    // 팔로우 정보 조회
	    vo.setFollowingId(id);
	    vo.setFollowId(followId);
	  
	    if ( mapper.checkFollow(vo)) {
	        isFollowing = true;
	    }

	    return isFollowing;
	}

	
	@PostMapping("/insertFollow/{followingId}")
	public Map<String, Object> insertFollow(@PathVariable("followingId") String followingId, @RequestParam("followId") String followId, FollowVO vo) {
	    vo.setFollowId(followId);
	    System.out.println(followId);
	    vo.setFollowingId(followingId);
	    System.out.println(followingId);
	    
	    Map<String, Object> resultMap = new HashMap<>();

	    mapper.insertFollow(vo);
	    return resultMap;
	}
	
	@PostMapping("/unFollow/{followingId}")
	public Map<String, Object> unFollow(@PathVariable("followingId") String followingId, @RequestParam("followId") String followId, FollowVO vo) {
	    vo.setFollowId(followId);
	    System.out.println(followId);
	    vo.setFollowingId(followingId);
	    System.out.println(followingId);
	    
	    Map<String, Object> resultMap = new HashMap<>();

	    mapper.unFollow(vo);
	    return resultMap;
	}
	
	
	@GetMapping("/followCount/{followId}")
	public Map<String, Object> followCount(@PathVariable("followId") String followId) {
	    
		return mapper.followCount(followId);
	}

	@PostMapping("/follower/{followId}")
		public List<Map<String, Object>> followerList(@PathVariable String followId) {
			return mapper.followerList(followId);
		}

	@PostMapping("/following/{followingId}")
		public List<Map<String, Object>> followingIdList(@PathVariable String followingId) {
		
		return mapper.followingList(followingId);
		}


}
