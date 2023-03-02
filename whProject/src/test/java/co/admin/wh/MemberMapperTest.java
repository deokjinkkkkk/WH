package co.admin.wh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.admin.wh.member.mapper.MemberMapper;
import co.admin.wh.member.vo.MemberVO;

@SpringBootTest
public class MemberMapperTest {

	@Autowired MemberMapper memberMapper;
	
////	@Test
//	public void 테스트() {
//		List<Map> map =  memberMapper.getMemberList();
//		System.out.println(map);
//	}

	@Test
	public void 로그인테스트() {
		MemberVO vo = new MemberVO();
		vo.setId("USER");
		vo.setPassword("1234");
		vo = memberMapper.memberSelect(vo);
		System.out.println(vo);
	}
}
