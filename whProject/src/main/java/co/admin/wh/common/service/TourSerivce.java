package co.admin.wh.common.service;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.common.vo.TourVO;
import co.admin.wh.member.vo.MemberVO;

public interface TourSerivce {
	int tourInsert(TourVO vo);
	List<TourVO> tourList(TourVO vo);
	int imgInsert(ImageVO ivo);
	int tourUpdate(TourVO vo);
	int tourAdminInsert(TourVO vo);
	
	List<TourVO>tourSearchList(TourVO vo);
	int getCountTotal(TourVO vo); //총게시글
}
