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
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		memberMapper.memberUpdate(vo);
		return "content/main";
	}

	@PostMapping("/memberDelete")
	public String memberDelete(MemberVO vo) {
		int Del = memberMapper.memDel(vo);
		if (Del == 1) {
			memberMapper.memberDelete(vo);
		} else {
			return "member/memberQuit";
		}

		return "content/main";
	}

	/*
	 * @GetMapping("/login/kakao") public String kakaoLogin(String code) {
	 * userService.kakaoLogin(code); return "redirect:/"; }
	 */
	@RequestMapping("/admemList")
	public String adMemList(MemberVO vo) {
		memberMapper.adMemberList();
		return "admin/memberAdmin";
	}

//	@RequestMapping("/login/kakao")
//	@ResponseBody
//	public String kakaoLogin(String code) {
//		String contentType = "application/x-www-form-urlencoded;charset=utf-8";
//		String restApi = "eca6df115c265db993762594751419b6";
//		String uri = "http://localhost/login/kakao";
//		RestTemplate resttemp = new RestTemplate();
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-type", contentType);
//
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("grant_type", "authorization_code");
//		params.add("client_id", restApi);
//		params.add("redirect_uri", uri);
//		params.add("code", code);
//		HttpEntity<MultiValueMap<String, String>> kakaoToken = new HttpEntity<>(params, headers);
//
//		ResponseEntity<String> response = resttemp.exchange(
//				"https://kauth.kakao.com/oauth/token", 
//				HttpMethod.POST,
//				kakaoToken,
//				String.class);
//
//		// 받아온 토큰 정보를 객체로 저장
//		ObjectMapper objectMapper = new ObjectMapper();
//		KakaoOAuthToken oauToken = null;
//		try {
//			oauToken = objectMapper.readValue(response.getBody(), KakaoOAuthToken.class);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		//토큰 정보를받아 프로필 요청하기
//		RestTemplate resttempPr = new RestTemplate();
//
//		HttpHeaders headersPr = new HttpHeaders();
//		headersPr.add("Authorication", "Bearer"+oauToken.getAccessToken());
//		headersPr.add("Content-type", contentType);
//		
//		HttpEntity<MultiValueMap<String, String>> kakaoProfile = new HttpEntity<>(headersPr);
//		
//		//사용자 정보 요청
//		ResponseEntity<String> responsePr = resttempPr.exchange(
//				"https://kauth.kakao.com/v2/user/me", 
//				HttpMethod.POST,
//				kakaoProfile,
//				String.class);
//		ObjectMapper objectMapper2 = new ObjectMapper();
//		KakaoProfile kakaoPro = null;
//		try {
//			kakaoPro = objectMapper2.readValue(responsePr.getBody(), KakaoProfile.class);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		
//		User kakaoUser = User.builder()
//				.username(kakaoPro.getKakao_account().getEmail()+ "_" + kakaoPro.getId())
//				.password("1234")

//				.build();
//				
//		
//		return "카카오" + response;
//	}
}
