package co.admin.wh.member.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.service.EmailService;
import co.admin.wh.member.vo.MemberSearchVO;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.Paging;

/*
 * 작성자 : 서덕진	
 * 작성일자 : 2023-03-20
 * 작성내용 : 회원 컨트롤러 :로그인,로그아웃,회원가입,비밀번호찾기,아이디찾기,이메일인증
*/



@Controller
public class MemberController {
	@Autowired
	MemberMapper memberMapper;

	@Autowired
	EmailService emailService;
	//로그인 폼 이동
	@RequestMapping("/login")
	public String loginForm() {
		return "member/login";
	}
	//아이디 찾기 폼 이동
	@RequestMapping("/idFind")
	public String idFindForm() {
		return "member/idFind";
	}
	//정지계정 로그인실패 폼 이동
	@RequestMapping("/disabled")
	public String disabledForm() {
		return "member/disabled";
	}
	//비밀번호 찾기 폼 이동
	@RequestMapping("/passFind")
	public String passFindForm() {
		return "member/passFind";
	}
	//로그아웃 세션 지우기
	@RequestMapping("/logout")
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "member/login";
	}
	//마이페이지 폼이동
	@RequestMapping("/myPage")
	public String myPageForm(Model model, MemberVO vo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("mem", memberMapper.memberSelect(vo));
		return "member/myPage";
	}
	//회원가입 폼이동
	@RequestMapping("/memberSignUpForm")
	public String signUpForm() {
		return "member/signUp";
	}
	//회원가입 
	@PostMapping("/memberSignUp")
	public String signUp(MemberVO vo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		memberMapper.memberInsert(vo);
		return "content/main";
	}
	//아이디 중복체크
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
	//회원 탈퇴 폼이동
	@RequestMapping("/memberQuitForm")
	public String memberQuitForm(Model model, MemberVO vo, Principal principal) {
		vo.setId(principal.getName());
		model.addAttribute("mem", memberMapper.memberSelect(vo));
		return "member/memberQuit";
	}
	//회원 정보 수정 폼 이동
	@RequestMapping("/memberUpdateForm")
	public String memberUpdateForm(Model model, MemberVO vo, Principal principal) {
		vo.setId(principal.getName());
		System.out.println(principal.getName() + "+++++++++++++++++++++++++++");
		model.addAttribute("mem", memberMapper.memberSelect(vo));
		return "member/memberUpdate";
	}
	//회원 정보 수정
	@PostMapping("/memberUpdate")
	public String memberUpdate(MemberVO vo) {
		if (vo.getPass() != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			vo.setPass(passwordEncoder.encode(vo.getPass()));
			memberMapper.memberUpdate(vo);
			return "content/main";
		}
		System.out.println(vo.getEmail() + "++++++++++++++++++++++++++++++++");
		memberMapper.memberUpdate(vo);
		return "redirect:/admemList";
	}
	//회원 탈퇴
	@PostMapping("/memberDelete")
	public String memberDelete(MemberVO vo,HttpServletRequest request) {
		HttpSession session = request.getSession();   


		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		int Del = memberMapper.memDel(vo);
		if (Del == 1) {
			memberMapper.memberDelete(vo);
			session.invalidate();
			SecurityContextHolder.getContext().setAuthentication(null);
		} else {
			return "member/memberQuit";
		}

		return "content/main";
	}
	//관리자 회원 리스트 출력
	@RequestMapping("/admemList")
	public String adMemList(MemberSearchVO vo, Model model, Paging paging) {
		paging.setPageUnit(5);//한 페이지에 출력할 레코드 건수
		paging.setPageSize(5); //한 페이지에 보여질 페이지 갯수
		
		System.out.println("PPPPPPPPPPPPPPPPPPPPP"+paging.getFirst() + paging.getLast()+ paging.getPageSize());
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println("PPPPPPPPPPPPPPPPPPPPP"+vo.getFirst() + vo.getLast());
		paging.setTotalRecord(memberMapper.getCountTotal(vo));
		
		model.addAttribute("mem", memberMapper.adMemberList(vo));
		return "admin/memberAdmin";
	}
	//회원 탈퇴 비밀번호 체크
	@RequestMapping("/passChk")
	@ResponseBody
	public String passCheck(MemberVO vo, boolean n) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		vo.setPass(passwordEncoder.encode(vo.getPass()));
		n = memberMapper.passChk(vo.getId(), vo.getPass());
		if (n) {
			return "success";
		} else {
			return "fail";
		}
	}
	//관리자 회원강제 탈퇴 처리
	@PostMapping("/adminDelete")
	public String adminDelete(MemberVO vo) {
		memberMapper.memDel(vo);
		memberMapper.memberDelete(vo);
		return "redirect:/admemList";
	}
	//회원가입 이메일 비밀번호 발급
	@PostMapping("/login/mailConfirm")
	@ResponseBody
	public String mailCheck(@RequestParam String email) throws Exception {
		String code = emailService.sendSimpleMessage(email);

		return code;
	}
	//비밀번호 찾기 이메일 비밀번호 발급
	@PostMapping("/login/passMail")
	public String passMail(@RequestParam String email, MemberVO vo) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String code;
		try {
			code = emailService.passFindMessage(email, vo);
			vo.setPass(passwordEncoder.encode(code));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		memberMapper.passUpdate(vo);
		
		return "redirect:/login";

	}
	//이메일 발급 확인 
	@PostMapping("/emailChk")
	@ResponseBody
	public String emailCheck(@RequestBody MemberVO vo, boolean n, Model model) {
		
		n = memberMapper.emailChk(vo.getId(), vo.getEmail());
		if (!n) {
			return "success";
		} else {
			return "fail";
		}
	}
	//아이디찾기
	@PostMapping("/idFind")
	@ResponseBody
	public String idFind(@RequestBody MemberVO vo, Model model) {
	    String id = memberMapper.idFind(vo);
	    return "아이디는 " + id + "입니다.";
	}
	
	//관리자 회원검색 아이디로 찾기
	@RequestMapping("/memberSearch")
	public String memberSearch(MemberVO vo, Model model, Paging paging) {
		paging.setPageUnit(10);//한 페이지에 출력할 레코드 건수
		paging.setPageSize(5); //한 페이지에 보여질 페이지 갯수
		
		System.out.println("PPPPPPPPPPPPPPPPPPPPP"+paging.getFirst() + paging.getLast()+ paging.getPageSize());
		vo.setFirst(paging.getFirst());
		vo.setLast(paging.getLast());
		System.out.println("PPPPPPPPPPPPPPPPPPPPP"+vo.getFirst() + vo.getLast());
		paging.setTotalRecord(memberMapper.getCountTotal(vo));
		
		model.addAttribute("mem", memberMapper.MemberSearchList(vo));
		return "admin/memberAdmin";
		
	}
	//관리자 아이디 제재
	@RequestMapping("/stateUpdate")
	public String adminStateUp(MemberVO vo) {
		memberMapper.memUpstate(vo);
		
		return "redirect:/admemList";
	}
}
