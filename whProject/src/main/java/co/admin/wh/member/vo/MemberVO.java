package co.admin.wh.member.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberVO {
	String id;
	String password;
	String name;
	int gender;
	int tel;
	String email;
	String perm;
	int state;
}