package sample.todo.resource.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import sample.todo.controller.ToDoController;
import sample.todo.model.ToDo;
import sample.todo.resource.ToDoResource;

@Component
public class ToDoResourceAssembler extends ResourceAssemblerSupport<ToDo, ToDoResource> {

	public ToDoResourceAssembler() {
		super(ToDo.class, ToDoResource.class);
	}

	public ToDoResource toResource(ToDo entity) {
		ToDoResource resource = new ToDoResource(entity);

		try {
			resource.add(linkTo(methodOn(ToDoController.class).get(entity.getId())).withSelfRel());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resource;
	}
}