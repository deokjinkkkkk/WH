package co.admin.wh.notice.mapper;

import java.util.List;

import co.admin.wh.notice.vo.VideoSearchVO;
import co.admin.wh.notice.vo.VideoVO;

public interface VideoMapper {

	List<VideoVO> videoSelect(VideoSearchVO svo); // 목록 출력

	int videoInsert(VideoVO vo); // 인서트

	VideoVO videoDetail(VideoVO vo); // 상세페이지

	int videoTotal(VideoSearchVO svo);// 페이지 수 관리
	
	int videoUpdate(VideoVO vo);//업데이트

	List<VideoVO> region(VideoSearchVO svo); //서울 정렬
	

	
	int videoHit(int videoCode); //조회수
}
