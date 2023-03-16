package co.admin.wh.diary.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.admin.wh.diary.service.FollowService;
import co.admin.wh.diary.vo.FollowVO;
import co.admin.wh.member.vo.MemberVO;

@RestController
public class FollowController {
	
	@Autowired FollowService service;
	
	//회원정보체크 하기
	@GetMapping("/checkFollow/{id}")
	public boolean checkFollow(@PathVariable("id") String id, FollowVO vo, MemberVO mvo, Principal principal) {
	System.out.println("=======check오나");
	
	boolean isFollowing = false; 
	
	return isFollowing;
	}
	
	//팔로우하기
	@PostMapping("/insertFollow/{id}")
	public FollowVO insertFollow(@PathVariable("id") String id, HttpSession session, FollowVO vo) {
	    MemberVO member = (MemberVO) session.getAttribute("id");
	    String followId = member.getId();
	    
	    vo.setFollowId(followId);
	    vo.setFollowingId(id);
	    
	    // FollowService를 통해 팔로우 정보를 DB에 저장
	    service.insertFollow(vo);
	    
	    // 저장된 FollowVO 객체 반환
	    return vo;
	}
	



	
	
}
