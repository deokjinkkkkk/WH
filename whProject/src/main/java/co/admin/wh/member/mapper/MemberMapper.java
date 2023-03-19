package co.admin.wh.member.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.FoodSearchVO;

public interface MemberMapper {
	
	boolean idChk(String id); //회원가입 아이디 중복검사
	
	boolean passChk(String id,String pass); //회원탈퇴 비밀번호 확인
	boolean emailChk(String id,String email); //비밀번호 찾기 이메일 인증
	
	String idFind(MemberVO vo); // 아이디 찾기
	
	int memberInsert(MemberVO vo); // 회원가입
	List<MemberVO> adMemberList(MemberVO vo); //관리자 회원 리스트 출력
	int memberUpdate(MemberVO vo); // 회원 정보 수정
	int passUpdate(MemberVO vo); //비밀번호 
	int memberDelete(MemberVO vo); //회원 탈퇴
	int memDel(MemberVO vo); //회원 탈퇴정보 담기
	MemberVO memberSelect(MemberVO vo); //로그인
	MemberVO memberSel(MemberVO vo); 
	List<MemberVO> MemberSearchList(MemberVO vo); //회원 검색 리스트
	int getCountTotal(MemberVO vo); //총게시글
	int memUpstate(MemberVO vo); //관리자 회원 수정
	
	
	
}
