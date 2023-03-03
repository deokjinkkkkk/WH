package co.admin.wh.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@PostMapping("/memberLogin")
	public String login(HttpSession session,MemberVO vo,UserDetailsService user) {
		vo = memberMapper.memberSelect(vo);
		
		return "content/main";
	}
	@RequestMapping("/logout")
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
	@RequestMapping("/memberSignUpForm")
	public String signUpForm() {
		return "member/signUp";
	}
	
	@PostMapping("/memberSignUp")
	public String signUp(MemberVO vo,PasswordEncoder passwordEncoder) {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		memberMapper.memberInsert(vo);
		return "content/main";
	}

}

