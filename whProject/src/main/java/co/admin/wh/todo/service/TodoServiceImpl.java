package co.admin.wh.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.admin.wh.todo.mapper.TodoMapper;
import co.admin.wh.todo.vo.TodoVO;

@Service
public class TodoServiceImpl implements TodoService	{

	@Autowired TodoMapper mapper;

	@Override
	public List<TodoVO> TodoList() {
			
		return mapper.TodoList();
	}

	@Override
	public int todoInsert(TodoVO vo) {
		
		return mapper.todoInsert(vo);
	}

	@Override
	public int todoDelete(TodoVO vo) {
		
		return mapper.todoDelete(vo);
	}


	@Override
	public int tagInsert(TodoVO vo) {
		return mapper.tagInsert(vo);
	}

	@Override
	public int todoCount(TodoVO vo) {
		
		return mapper.todoCount(vo);
	}

	@Override
	public int todoComplete(TodoVO vo) {
		return mapper.todoComplete(vo);
	}


	

}
