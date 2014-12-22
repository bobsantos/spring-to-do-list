package sample.todo.resource;

import sample.todo.model.ToDo;	

import org.springframework.hateoas.Resource;

public class ToDoResource extends Resource<ToDo> {

	private String title;
	private String description;

	public ToDoResource(ToDo entity) {
		super(entity);
	}

	public String getTitle(){
		return this.title;
	}

	public String getDescription(){
		return this.description;
	}
}