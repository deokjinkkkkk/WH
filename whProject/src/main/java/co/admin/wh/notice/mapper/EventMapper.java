package co.admin.wh.notice.mapper;

public interface EventMapper {
	
	List<EventVO> getEventList(EventVO vo); //전체조회
	EventVO detailSe(EventVO evo); //상세보기
	
	int eventInsert(EventVO vo);//글등록
	int imgInsert(ImageVO ivo); //이미지 등록
	int EventUpdate(EventVO vo);//수정
	int EventDelete(EventVO vo);//삭제
	
	int getCountTotal(EventSearchVO svo);//총 게시글
	int hitUpdate(int eventCode);//조회수
	
}
