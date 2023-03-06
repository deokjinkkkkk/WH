package co.admin.wh.member.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.admin.wh.member.vo.MemberVO;
@Repository
public interface MemberService {
	MemberVO memberSelect(MemberVO vo);
	boolean idChk(String id);
	int memberInsert(MemberVO vo);
	List<MemberVO> memberList();
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memDel(MemberVO vo);
}
