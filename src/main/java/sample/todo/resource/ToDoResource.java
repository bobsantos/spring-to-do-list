package sample.todo.resource;

import org.springframework.hateoas.Resource;

import sample.todo.model.ToDo;

public class ToDoResource extends Resource<ToDo> {
	public ToDoResource(ToDo entity) {
		super(entity);
	}
}