package co.admin.wh.member.mapper;

import java.util.List;
import java.util.Map;

import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.member.vo.MemberVO;
import co.admin.wh.notice.vo.FoodSearchVO;

public interface MemberMapper {
	boolean idChk(String id);
	boolean passChk(String id,String pass);
	boolean emailChk(String id,String email);
	int memberInsert(MemberVO vo);
	List<MemberVO> adMemberList(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int passUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memDel(MemberVO vo);
	MemberVO memberSelect(MemberVO vo);
	MemberVO memberSel(MemberVO vo);
	List<MemberVO> MemberSearchList(MemberVO vo);
	int getCountTotal(MemberVO vo); //총게시글
	
}
