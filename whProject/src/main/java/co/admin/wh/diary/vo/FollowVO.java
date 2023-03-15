package co.admin.wh.diary.vo;

import lombok.Data;

@Data
public class FollowVO {
	public String followId;  //신청 받은(친구 신청 받은)
	public String followingId;  //내가 팔로우 한 회원 (로그인한 회원)
}
