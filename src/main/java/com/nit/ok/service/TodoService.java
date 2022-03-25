package com.nit.ok.service;

import javax.validation.ConstraintViolationException;


import com.nit.ok.model.TodoApp;

public interface TodoService {

	public void createTodo(TodoApp todo) throws NullPointerException;
}
