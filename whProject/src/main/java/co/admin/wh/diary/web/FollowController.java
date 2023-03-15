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
	
	@GetMapping("/checkFollow/{id}")
	public boolean  checkFollow(@PathVariable("id")String id, Model model, MemberVO mvo,Principal principal, FollowVO vo) {
	   System.out.println("========체크 오나");
		boolean check = false;
	    mvo.setId(principal.getName());
		if(service.checkFollow(vo)) {
			check = true;
		}
	    
	    return check;
	}

	

	
	//팔로우 신청하기
	@PostMapping("/follow/{id}")
	public FollowVO follow(FollowVO vo, HttpSession session, @PathVariable String id) {
		System.out.println("=============신청하기");
		vo.setFollowId((String)session.getAttribute(id));
		vo.setFollowingId(id);
		
		service.insertFollow(vo);
		return vo;
		
	}
	
}
