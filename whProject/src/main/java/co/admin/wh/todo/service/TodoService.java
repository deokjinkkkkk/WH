package co.admin.wh.todo.service;

import java.util.List;
import java.util.Map;

import co.admin.wh.hotel.vo.HotelSearchVO;
import co.admin.wh.hotel.vo.HotelVO;
import co.admin.wh.todo.vo.TodoVO;

public interface TodoService {


	int todoCount(TodoVO vo);
	
	List<TodoVO> TodoList();//전체조회
	
	int todoInsert(TodoVO vo);//등록
	
	int todoDelete(TodoVO vo);//삭제
	
	int todoComplete(TodoVO vo); //완료
	
	List<Map<String, Object>>tagSearch(Map<String, Object> paramMap) throws Exception; //태그 검색 자동완성

	int tagInsert(TodoVO vo);
	
	public int getMaxNo(); // 게시물 max 번호
	
	
	
}
