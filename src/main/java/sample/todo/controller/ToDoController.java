package sample.todo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.todo.model.ToDo;
import sample.todo.repository.ToDoRepository;
import sample.todo.resource.ToDoResource;
import sample.todo.resource.assembler.ToDoResourceAssembler;

@Controller
@RequestMapping("/todos")
public class ToDoController {

	@Autowired
	private ToDoRepository repository;
	
	@Autowired
	private ToDoResourceAssembler assembler;
	
	@RequestMapping(method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<List<ToDoResource>> list(){
		return new ResponseEntity<List<ToDoResource>>(assembler.toResources(repository.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public HttpEntity<ToDoResource> post(@RequestBody ToDo entity){
		return new ResponseEntity<ToDoResource>(assembler.toResource(repository.save(entity)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public HttpEntity<ToDoResource> get(@PathVariable String id){
		return new ResponseEntity<ToDoResource>(assembler.toResource(repository.findOne(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces={MediaType.APPLICATION_JSON_VALUE})
	public HttpEntity<ToDoResource> update(@PathVariable String id, @RequestBody ToDo todo){
		ToDo updated = repository.save(todo);
		return new ResponseEntity<ToDoResource>(assembler.toResource(updated), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public HttpEntity<Void> delete(@PathVariable String id){
		repository.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}