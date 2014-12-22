package sample.todo.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import sample.todo.model.ToDo;
import sample.todo.resource.ToDoResource;
import sample.todo.controller.ToDoController;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class ToDoResourceAssembler implements ResourceAssembler<ToDo, ToDoResource> {

	public ToDoResource toResource(ToDo entity) {
		ToDoResource resource = new ToDoResource(entity);

		try {
			resource.add(linkTo(methodOn(ToDoController.class).list()).withSelfRel());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resource;
	}
}