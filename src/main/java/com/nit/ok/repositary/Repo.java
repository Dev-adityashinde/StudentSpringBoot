package com.nit.ok.repositary;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nit.ok.model.TodoApp;

@Repository
public interface Repo extends MongoRepository<TodoApp,Integer> {

	@Query("{'todo': ?0}")
	Optional<TodoApp> findByTodo(String todo);
}
