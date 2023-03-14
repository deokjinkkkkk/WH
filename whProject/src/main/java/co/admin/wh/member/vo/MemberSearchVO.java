package co.admin.wh.member.vo;

import lombok.Data;

@Data
public class MemberSearchVO extends MemberVO{
	Integer first;  //페이징 
	Integer last;
}
