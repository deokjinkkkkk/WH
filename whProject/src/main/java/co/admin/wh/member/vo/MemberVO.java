package co.admin.wh.member.vo;

import lombok.Data;

@Data
public class MemberVO {
	String id;
	String password;
	String name;
	int gender;
	int tel;
	String email;
	int perm;
	int state;
	
}
