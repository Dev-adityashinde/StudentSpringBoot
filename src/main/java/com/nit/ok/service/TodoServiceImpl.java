package com.nit.ok.service;

import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.ok.exception.CustomExe;
import com.nit.ok.model.TodoApp;
import com.nit.ok.repositary.Repo;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	Repo repo;
	
	@Override
	public void createTodo(TodoApp todo) throws ConstraintViolationException, CustomExe {
		
		Optional<TodoApp> Optodo=repo.findByTodo(todo.getTodo());
		
		if(Optodo.isPresent())
		{
			throw new CustomExe(CustomExe.todoAlreadyexists());
		}
		else
		{
			todo.setCreateDt(new Date(System.currentTimeMillis()));
			repo.save(todo);
		}
		

	}

}
