package co.admin.wh.member.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.member.vo.MemberVO;

public interface MemberMapper {
	List<Map> getMemberList();
	MemberVO memberSelect(MemberVO vo);
}
