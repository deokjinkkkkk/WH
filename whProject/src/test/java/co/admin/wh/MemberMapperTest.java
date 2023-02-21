package co.admin.wh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.service.MemberVO;

@SpringBootTest
public class MemberMapperTest {

	@Autowired MemberMapper memberMapper;
	

	@Test
	public void 로그인테스트(MemberVO vo) {
		vo = memberMapper.memberSelect(vo);
		System.out.println(vo);
	}
}
