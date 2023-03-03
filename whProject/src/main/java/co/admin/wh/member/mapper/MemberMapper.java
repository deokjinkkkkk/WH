package co.admin.wh.member.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.member.vo.MemberVO;

public interface MemberMapper {
	MemberVO memberSelect(MemberVO vo);
	boolean idChk(String id);
	int memberInsert(MemberVO vo);
	List<MemberVO> memberList();
}
