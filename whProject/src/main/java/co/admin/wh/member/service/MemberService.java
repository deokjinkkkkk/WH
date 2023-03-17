package co.admin.wh.member.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.FoodSearchVO;

public interface MemberService {
	
	boolean idChk(String id);
	boolean passChk(String id,String pass);
	boolean emailChk(String id,String email);
	int memberInsert(MemberVO vo);
	
	List<MemberVO> adMemberList(MemberVO vo);
	
	int memberUpdate(MemberVO vo);
	int passUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memDel(MemberVO vo);
	
	MemberVO memberSel(MemberVO vo);
	List<MemberVO>MemberSearchList(MemberVO vo);
	int getCountTotal(MemberVO vo); //총게시글

}
