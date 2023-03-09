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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.todo.mapper.TodoMapper;
import co.admin.wh.todo.vo.TodoVO;

@Controller
public class TodoController {
	@Autowired TodoMapper todoMapper;
	
	//todolist 페이지
	@RequestMapping("/todolist") 
	public String todoList(Model model) {
		
		model.addAttribute("todoList", todoMapper.TodoList());
		return "todo/todoList";
	}
	
	
	//db에 담긴 todolist 띄우기
	@GetMapping("/todoGetList")
	@ResponseBody
	public List<TodoVO> todoCount(@RequestParam int todoFlag, TodoVO vo, Model model) {
		List<TodoVO> todoList = todoMapper.TodoList(todoFlag);
		
		return todoList;
	}
	
	
	
	//리스트 담기
		@PostMapping("/todoInsert")
		@ResponseBody
		public String todoInsert(@RequestBody TodoVO vo) {
			int result = todoMapper.todoInsert(vo);
			String resultValue = "fail";
			if(result > 0) {
				resultValue = "success";
			}
			return resultValue;
		}
	
	
	//더블클릭 시 완료 여부 반영
	@RequestMapping("/todoComplete")
	@ResponseBody
	public String todoComplete(@RequestBody TodoVO vo) {
		todoMapper.todoComplete(vo);
		return "success";
	}
	
	//구분 : 1.전체리스트 2.완료리스트
	@PostMapping("/updateTodoFlag")
	@ResponseBody
	public String updateTodoFlag(@RequestBody TodoVO vo) {
		todoMapper.updateTodoFlag(vo);
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