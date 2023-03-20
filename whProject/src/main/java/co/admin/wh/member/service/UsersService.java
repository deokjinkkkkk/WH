package co.admin.wh.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;


@Service
public class UsersService implements UserDetailsService {
	@Autowired MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVO vo = new MemberVO();
		vo.setId(username);
		vo = mapper.memberSelect(vo);
		if(vo == null) {
			throw new UsernameNotFoundException("유저 없음");
		}
		
		return vo;
		
	}
}
