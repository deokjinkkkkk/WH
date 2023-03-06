package co.admin.wh.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired 
	private MemberMapper member;

	@Override
	public MemberVO memberSelect(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memberSelect(vo);
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memberInsert(vo);
	}

	@Override
	public List<MemberVO> memberList() {
		// TODO Auto-generated method stub
		return member.memberList();
	}

	@Override
	public boolean idChk(String id) {
		// TODO Auto-generated method stub
		return member.idChk(id);
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memberUpdate(vo);
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memberDelete(vo);
	}

	@Override
	public int memDel(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memDel(vo);
	}


	
	

}
