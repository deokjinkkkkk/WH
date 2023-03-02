package co.admin.wh.member.service;

import org.springframework.stereotype.Repository;

import co.admin.wh.member.vo.MemberVO;
@Repository
public interface MemberService {
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
}
