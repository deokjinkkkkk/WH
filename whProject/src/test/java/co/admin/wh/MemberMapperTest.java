package co.admin.wh;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.admin.wh.member.mapper.MemberMapper;

@SpringBootTest
public class MemberMapperTest {

	@Autowired MemberMapper memberMapper;
	
	@Test
	public void 테스트() {
		List<Map> map = memberMapper.getMemberList();
		System.out.println(map);
	}
}
