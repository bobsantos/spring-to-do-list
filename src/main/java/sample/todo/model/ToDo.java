package sample.todo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="todos")
public class ToDo {
	@Id
	private String id;
	
	private String title;
	private String description;
	private boolean done;
	
	public ToDo() {}

	public ToDo(String title, String description, boolean done) {
		this.title = title;
		this.description = description;
		this.done = done;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", description="
				+ description + ", done=" + done + "]";
	}
}