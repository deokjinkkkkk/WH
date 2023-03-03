package co.admin.wh.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.common.vo.ImageVO;
import co.admin.wh.notice.mapper.EventMapper;
import co.admin.wh.notice.vo.EventSearchVO;
import co.admin.wh.notice.vo.EventVO;

@Service
public class EventServiceImpl implements EventService {
@Autowired private EventMapper mapper;

//전체보기
@Override
public List<EventVO> getEventList(EventSearchVO svo) {
	return mapper.getEventList(svo);
}

//상세보기
@Override
public EventVO detailSelect(EventVO evo) {
	return mapper.detailSelect(evo);
}

//등록
@Override
public int eventInsert(EventVO vo) {
	return mapper.eventInsert(vo);
}

//수정
@Override
public int eventUpdate(EventVO vo) {
	return mapper.eventUpdate(vo);
}

//삭제
@Override
public int eventDelete(EventVO vo) {
	return mapper.eventDelete(vo);
}

//이미지 등록
@Override
public int imgInsert(ImageVO ivo) {
	return mapper.imgInsert(ivo);
}

//총 게시물
@Override
public int getCountTotal(EventSearchVO svo) {
	return mapper.getCountTotal(svo);
}

//조회수
@Override
public int hitUpdate(int eventCode) {
	return mapper.hitUpdate(eventCode);
}

}