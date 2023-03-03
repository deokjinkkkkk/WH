package co.admin.wh.todo.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import co.admin.wh.todo.service.TodoService;
import co.admin.wh.todo.vo.TodoVO;


@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todolist")
    @ResponseBody
    public List<TodoVO> getTodoList(TodoVO vo) {
        return todoService.getTodoList(vo);
    }

    @PostMapping("/todolist")
    @ResponseBody
    public int todoInsert(@RequestBody TodoVO vo) {
        return todoService.todoInsert(vo);
    }

    @PutMapping("/todolist/{todoCode}")
    @ResponseBody
    public int todoUpdate(@PathVariable("todoCode") int todoCode) {
        return todoService.todoUpdate(todoCode);
    }

    @DeleteMapping("/todolist/{todoCode}")
    @ResponseBody
    public int todoDelete(@PathVariable("todoCode") int todoCode) {
        return todoService.todoDelete(todoCode);
    }
}