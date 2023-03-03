package co.admin.wh.todo.mapper;

import java.util.List;

import co.admin.wh.todo.vo.TodoVO;

public interface TodoMapper {

	List<TodoVO> getTodoList(TodoVO vo);//전체조회
	
	int todoInsert(TodoVO vo);//등록
	
	int todoDelete(int todoCode);//삭제
	
	int todoUpdate(TodoVO vo);//수정
}
