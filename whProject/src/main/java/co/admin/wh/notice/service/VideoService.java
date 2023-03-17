package co.admin.wh.notice.service;

import java.util.List;

import co.admin.wh.notice.vo.VideoSearchVO;
import co.admin.wh.notice.vo.VideoVO;


public interface VideoService {
	
	List<VideoVO> videoSelect(VideoVO vo); //목록 출력
	
	int videoInsert(VideoVO vo); //인서트
	
	VideoVO videoDetail(VideoVO vo); //상세페이지
	
	int videoTotal(VideoSearchVO svo);//페이지 수 관리
	
	int videoUpdate(VideoVO vo);//업데이트
	
	List<VideoVO> seoul(VideoSearchVO svo); //서울 정렬
	
	List<VideoVO> busan(VideoSearchVO svo); //부산 정렬
	
	List<VideoVO> daegu(VideoSearchVO svo); //대구 정렬

	List<VideoVO> incheon(VideoSearchVO svo); //인천 정렬

	List<VideoVO> gwangju (VideoSearchVO svo); //광주 정렬

	List<VideoVO> daejeon (VideoSearchVO svo); //대전 정렬

	List<VideoVO> ulsan (VideoSearchVO svo); //울산 정렬

	List<VideoVO> sejong(VideoSearchVO svo); //세종 정렬

	List<VideoVO> gyeonggi(VideoSearchVO svo); //경기 정렬

	List<VideoVO> gangwon(VideoSearchVO svo); //강원 정렬

	List<VideoVO> chungbuk(VideoSearchVO svo); //충북 정렬

	List<VideoVO> chungnam(VideoSearchVO svo); //충남 정렬

	List<VideoVO> jeonbuk(VideoSearchVO svo); //전북 정렬

	List<VideoVO> jeonnam(VideoSearchVO svo); //전남 정렬

	List<VideoVO> kyungbuk(VideoSearchVO svo); //경북 정렬

	List<VideoVO> kyungnam(VideoSearchVO svo); //경남 정렬

	List<VideoVO> jeju (VideoSearchVO svo); //제주 정렬
	
	int videoHit(int videoCode); //조회수
}
