package co.admin.wh.member.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.member.mapper.MemberMapper;

import co.admin.wh.member.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired MemberMapper memberMapper;
	
	@RequestMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	@RequestMapping("/logout")
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "member/login";
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
	public String signUp(MemberVO vo) {
		memberMapper.memberInsert(vo);
		return "content/main";
	}
	@RequestMapping("/memberIdChk")
	@ResponseBody
	public String idCheck(Model model, MemberVO vo, boolean n) {

		n = memberMapper.idChk(vo.getId());
		if (n) {
			return "success";
		}else {
			return "fali";
		}
	}
	
	@RequestMapping("/memberQuitForm")
	public String memberQuitForm() {
		return "member/memberQuit";
	}
	
	@RequestMapping("/memberUpdateForm")
	public String memberUpdateForm() {
		return "member/memberUpdate";
	}
	
	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberVO vo) {
		memberMapper.memberDelete(vo);
		return "content/main";
	}
	
	@PostMapping("/memberDelete")
	public String memberDelete(MemberVO vo) {
		int Del = memberMapper.memDel(vo);
		if(Del == 1) {
			memberMapper.memberDelete(vo);
		}else {
			return "member/memberQuit";
		}
		
		return "content/main";
	}

}

