package co.admin.wh.member.service;

import java.io.Serializable;

import co.admin.wh.member.vo.MemberVO;

public class SessionUser implements Serializable {
	
	private String id;
	private String role;
	
	public SessionUser(MemberVO member) {
		this.id = member.getId();
		this.role = member.getPerm();
	}
	
	public SessionUser() {}
}
