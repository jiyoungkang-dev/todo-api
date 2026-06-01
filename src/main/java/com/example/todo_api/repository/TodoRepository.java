package com.example.todo_api.repository;

import com.example.todo_api.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// 할 일 DB 접근 (JPA)
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
