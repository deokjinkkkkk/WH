package co.admin.wh;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import co.admin.wh.tag.service.TagService;

@SpringBootTest
public class TagServiceTest {

	@Autowired TagService tagservice;
	
	@Test
	@Transactional
	@Commit
	public void 태그테스트() {
		tagservice.saveTag("여기는 내용입니다 #순심 #경아 #바다", 1);
	}
}
