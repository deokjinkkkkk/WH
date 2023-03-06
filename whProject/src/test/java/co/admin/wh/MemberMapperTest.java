package co.admin.wh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(); //4~31 까지설정가능 올릴수록 시간이 많이든다.
		String result = bcrypt.encode("1234"); //암호화 할때마다 매번 결과가 달라진다.
		Boolean matchYn = bcrypt.matches("1112", result); //(원래패스워드,암호화된 패스워드)
		System.out.println(result);
		System.out.println(matchYn);
	
	}
}
