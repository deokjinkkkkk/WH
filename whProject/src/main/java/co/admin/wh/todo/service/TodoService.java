package co.admin.wh.todo.service;

import java.util.List;

import co.admin.wh.todo.vo.TodoVO;

public interface TodoService {

	int todoCount(TodoVO vo);
	
	List<TodoVO> TodoList();//전체조회
	
	int todoInsert(TodoVO vo);//등록
	
	int todoDelete(TodoVO vo);//삭제
	
	int todoUpdate(TodoVO vo);//수정
	
	int tagInsert(TodoVO vo);
	
}
