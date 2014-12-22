package sample.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sample.todo.model.ToDo;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {
	public ToDo findByTitle(String title);
}
