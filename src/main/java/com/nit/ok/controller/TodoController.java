 package com.nit.ok.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nit.ok.model.TodoApp;
import com.nit.ok.repositary.Repo;
import com.nit.ok.service.TodoService;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@RestController
public class TodoController {

	@Autowired
	private Repo repo;
	
	@Autowired
	private TodoService service;
	
	@GetMapping("/todos")
	public ResponseEntity<?> getAlltodos()
	{
		List<TodoApp> todos=repo.findAll();
		if(todos.size()>0)
		{
			return new ResponseEntity<List<TodoApp>>(todos, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("no todos avaialbe ", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/todos")
	public ResponseEntity<?>  CheckAndSave(@RequestBody TodoApp todo)
	{
		
		try
		{
		    service.createTodo(todo);
		     return new ResponseEntity<TodoApp>(todo, HttpStatus.OK);
		}
		catch(Exception e)
		{
			 return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<?> getRecord(@PathVariable("id") int id)
	{
		
		Optional<TodoApp> todo=repo.findById(id);
		if(todo.isPresent())
		{
			return new ResponseEntity<TodoApp>(todo.get(), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(id+" this id is not found", HttpStatus.NOT_FOUND);
		}
	}
	
}
