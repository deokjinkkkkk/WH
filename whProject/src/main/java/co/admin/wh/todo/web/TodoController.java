package co.admin.wh.todo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.todo.mapper.TodoMapper;
import co.admin.wh.todo.vo.TodoVO;

@Controller
public class TodoController {
	@Autowired TodoMapper todoMapper;
	
	
	@RequestMapping("/todolist") //todolist창 이동
	public String todoList(Model model) {
		
		model.addAttribute("todoList", todoMapper.TodoList());
		return "todo/todoList";
	}
	
	
	
	
	//리스트 띄우기
	@GetMapping("/todoGetList")
	@ResponseBody
	public List<TodoVO> todoCount(TodoVO vo, Model model) {
		List<TodoVO> todoList = todoMapper.TodoList();
		
		return todoList;
	}
	//리스트 등록하기
	@PostMapping("/todoInsert")
	@ResponseBody
	public String todoInsert(@RequestBody TodoVO vo) {
		todoMapper.todoInsert(vo);
		return "success";
	}
	
	//삭제
	@DeleteMapping("/todoDelete")
	@ResponseBody
	public String todoDelete(TodoVO vo) {
		todoMapper.todoDelete(vo);
		return " success";
	}
	
	@RequestMapping("/todoCount")
	@ResponseBody
	public int todoCounter(TodoVO vo) {
		int count = todoMapper.todoCount(vo);
		return count;
	}
  
}