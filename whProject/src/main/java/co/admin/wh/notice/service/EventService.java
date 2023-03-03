package co.admin.wh.notice.service;

import java.util.List;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.vo.EventSearchVO;
import co.admin.wh.notice.vo.EventVO;

public interface EventService {
	
	List<EventVO> getEventList(EventSearchVO svo); //전체조회
	EventVO detailSelect(EventVO evo); //상세보기
	
	int eventInsert(EventVO vo);//글등록
	int eventUpdate(EventVO vo);//수정
	int eventDelete(EventVO vo);//삭제
	int imgInsert(ImageVO ivo); //이미지 등록
	
	int getCountTotal(EventSearchVO svo);//총 게시글
	int hitUpdate(int eventCode);//조회수
}
