package com.nit.ok.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.ok.model.TodoApp;
import com.nit.ok.repositary.Repo;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	Repo repo;
	
	@Override
	public void createTodo(TodoApp todo) throws NullPointerException {
		
		Optional<TodoApp> Optodo=repo.findByTodo(todo.getTodo());
		
		if(Optodo.isPresent())
		{
			throw new NullPointerException("Data not exist");
		}
		else
		{
			todo.setCreateDt(new Date(System.currentTimeMillis()));
			repo.save(todo);
		}
		

	}

}
