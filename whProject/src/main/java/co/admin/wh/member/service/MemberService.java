package co.admin.wh.member.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import co.admin.wh.member.vo.MemberVO;
@Repository
public interface MemberService {
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
	List<MemberVO> memberList();
}
