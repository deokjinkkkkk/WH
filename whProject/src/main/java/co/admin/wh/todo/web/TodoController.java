package co.admin.wh.todo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.admin.wh.todo.mapper.TodoMapper;

@Controller
public class TodoController {
	@Autowired TodoMapper todoMapper;
	
	
	@RequestMapping("/todolist") //todolist창 이동
	public String todoList(Model model) {
		model.addAttribute("todoList", todoMapper.TodoList());
		return "todo/todoList";
	}
  
}