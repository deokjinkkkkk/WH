package co.admin.wh.member.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.admin.wh.member.vo.MemberVO;

public interface MemberService {
	boolean idChk(String id);
	int memberInsert(MemberVO vo);
	List<MemberVO> adMemberList();
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memDel(MemberVO vo);
	MemberVO memberSel(MemberVO vo);
}
