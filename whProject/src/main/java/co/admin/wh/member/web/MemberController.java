package co.admin.wh.member.web;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.KakaoOAuthToken;
import co.admin.wh.member.vo.KakaoProfile;
import co.admin.wh.member.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberMapper memberMapper;

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
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		memberMapper.memberInsert(vo);
		return "content/main";
	}

	@RequestMapping("/memberIdChk")
	@ResponseBody
	public String idCheck(MemberVO vo, boolean n) {

		n = memberMapper.idChk(vo.getId());
		if (n) {
			return "success";
		} else {
			return "fali";
		}
	}

	@RequestMapping("/memberQuitForm")
	public String memberQuitForm(Model model, MemberVO vo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("mem", memberMapper.memberSelect(vo));
		return "member/memberQuit";
	}

	@RequestMapping("/memberUpdateForm")
	public String memberUpdateForm(Model model, MemberVO vo, Principal principal) {
		vo.setId(principal.getName());
		System.out.println(principal.getName() + "+++++++++++++++++++++++++++");
		model.addAttribute("mem", memberMapper.memberSelect(vo));
		return "member/memberUpdate";
	}

	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberVO vo) {
		if(vo.getPass() != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			vo.setPass(passwordEncoder.encode(vo.getPass()));
			memberMapper.memberUpdate(vo);
			return "content/main";
		}
		System.out.println(vo.getEmail()+"++++++++++++++++++++++++++++++++");
		memberMapper.memberUpdate(vo);
		return "redirect:/admemList";
	}

	@PostMapping("/memberDelete")
	public String memberDelete(MemberVO vo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		int Del = memberMapper.memDel(vo);
		if (Del == 1) {
			memberMapper.memberDelete(vo);
		} else {
			return "member/memberQuit";
		}

		return "content/main";
	}

	@RequestMapping("/admemList")
	public String adMemList(MemberVO vo, Model model) {
		model.addAttribute("mem",memberMapper.adMemberList());
		return "admin/memberAdmin";
	}
	
	@RequestMapping("/passChk")
	@ResponseBody
	public String passCheck(MemberVO vo,boolean n) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		n = memberMapper.passChk(vo.getId(),vo.getPass());
		if (n) {
			return "success";
		} else {
			return "fali";
		}
	}
	
	@PostMapping("/adminDelete")
	public String adminDelete(MemberVO vo) {
		memberMapper.memDel(vo);
		memberMapper.memberDelete(vo);
		return "redirect:/admemList";
	}
}
