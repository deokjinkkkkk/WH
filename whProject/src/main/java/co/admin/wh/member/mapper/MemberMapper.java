package co.admin.wh.member.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.member.vo.MemberVO;

public interface MemberMapper {
	boolean idChk(String id);
	boolean passChk(String id,String pass);
	boolean emailChk(String id,String email);
	int memberInsert(MemberVO vo);
	List<MemberVO> adMemberList();
	int memberUpdate(MemberVO vo);
	int passUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memDel(MemberVO vo);
	MemberVO memberSelect(MemberVO vo);
	MemberVO memberSel(MemberVO vo);
}
