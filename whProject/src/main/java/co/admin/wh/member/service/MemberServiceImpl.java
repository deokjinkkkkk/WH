package co.admin.wh.member.service;

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


	
	

}
