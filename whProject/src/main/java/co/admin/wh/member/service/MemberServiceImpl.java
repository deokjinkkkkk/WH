package co.admin.wh.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.FoodSearchVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired 
	private MemberMapper member;


	@Override
	public int memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memberInsert(vo);
	}

	@Override
	public List<MemberVO> adMemberList(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.adMemberList(vo);
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

	@Override
	public MemberVO memberSel(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memberSel(vo);
	}

	@Override
	public boolean passChk(String id,String pass) {
		// TODO Auto-generated method stub
		return member.passChk(id,pass);
	}

	@Override
	public int passUpdate(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.passUpdate(vo);
	}

	@Override
	public boolean emailChk(String id, String email) {
		// TODO Auto-generated method stub
		return member.emailChk(id, email);
	}

	@Override
	public int getCountTotal(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.getCountTotal(vo);
	}

	@Override
	public List<MemberVO> MemberSearchList(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.MemberSearchList(vo);
	}

	@Override
	public int memUpstate(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.memUpstate(vo);
	}

	@Override
	public String idFind(MemberVO vo) {
		// TODO Auto-generated method stub
		return member.idFind(vo);
	}

}
