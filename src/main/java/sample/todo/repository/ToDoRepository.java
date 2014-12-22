package sample.todo.repository;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import sample.todo.model.ToDo;

@Repository
@Document(collection="todo")
public interface ToDoRepository extends MongoRepository<ToDo, String> {
	public ToDo findByTitle(String title);
}
