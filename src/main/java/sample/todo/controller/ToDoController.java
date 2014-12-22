package sample.todo.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import sample.todo.model.ToDo;
import java.util.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ToDoController {

	private static final List<ToDo> todos;

	static {
		todos = new ArrayList<ToDo>();

		ToDo todo = new ToDo();
		todo.setTitle("To do 1");
		todo.setDescription("This is a test to do");

		todos.add(todo);
	}

	@RequestMapping("/todos")
	@ResponseBody
	public HttpEntity<List<ToDo>> list(){
		return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);
	}
}