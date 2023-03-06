package co.admin.wh.todo.mapper;

import java.util.List;

import co.admin.wh.todo.vo.TodoVO;

public interface TodoMapper {

	List<TodoVO> TodoList();//전체조회
	
	int todoInsert(TodoVO vo);//등록
	
	int todoDelete(TodoVO vo);//삭제
	
	int todoUpdate(TodoVO vo);//수정
}
