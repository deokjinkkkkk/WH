package co.admin.wh.todo.mapper;

import java.util.List;

import co.admin.wh.todo.vo.TodoVO;

public interface TodoMapper {

	int todoCount(TodoVO vo);
	
	List<TodoVO> TodoList();//전체조회
	
	int todoInsert(TodoVO vo);//등록
	
	int todoDelete(TodoVO vo);//삭제
	
	int todoComplete(TodoVO vo); //완료
	
	int tagInsert(TodoVO vo);

	List<TodoVO> TodoList(int todoFlag);

	int updateTodoFlag(TodoVO vo); // 상태변경 설정
	
	public int getMaxNo(); // 게시물 max 번호
}
