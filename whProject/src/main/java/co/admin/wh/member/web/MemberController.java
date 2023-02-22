package co.admin.wh.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired MemberMapper memberMapper;
	
	@RequestMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	@PostMapping("")
	public String login(HttpSession session,MemberVO vo) {
		
		vo = memberMapper.memberSelect(vo);
		if(vo != null) {
			session.setAttribute("id", vo.getId());
			session.setAttribute("perm", vo.getPerm());
			session.setAttribute("name", vo.getName());
		}else {
			return "member/login";
		}
		return "content/main";
	}
	@RequestMapping("/memberLogout.do")
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	@RequestMapping("/contract")
	public String contractForm() {
		return "member/contract";
	}
	@RequestMapping("/myPage")
	public String myPageForm() {
		return "member/myPage";
	}
	@RequestMapping("/signUp")
	public String signUpForm() {
		return "member/signUp";
	}
}

