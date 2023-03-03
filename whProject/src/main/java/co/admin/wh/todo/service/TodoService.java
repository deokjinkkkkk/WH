package co.admin.wh.todo.service;

import java.util.List;

import co.admin.wh.todo.vo.TodoVO;

public interface TodoService {

	List<TodoVO> getTodoList(TodoVO vo);//전체조회
	
	int todoInsert(TodoVO vo);//등록
	
	int todoDelete(int todoCode);//삭제
	
	int todoUpdate(int todoCode);//수정
}
