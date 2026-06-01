package com.example.todo_api.service;

import com.example.todo_api.dto.TodoRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.entity.Todo;
import com.example.todo_api.exception.TodoNotFoundException;
import com.example.todo_api.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 할 일 비즈니스 로직
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // 생성
    public TodoResponse create(TodoRequest request) {
        Todo todo = new Todo(request.getTitle());
        todo.setCompleted(request.isCompleted());
        return TodoResponse.from(todoRepository.save(todo));
    }

    // 전체 조회
    public List<TodoResponse> findAll() {
        return todoRepository.findAll().stream()
                .map(TodoResponse::from)
                .toList();
    }

    // 단건 조회
    public TodoResponse findById(Long id) {
        return TodoResponse.from(getTodoOrThrow(id));
    }

    // 수정
    public TodoResponse update(Long id, TodoRequest request) {
        Todo todo = getTodoOrThrow(id);
        todo.setTitle(request.getTitle());
        todo.setCompleted(request.isCompleted());
        return TodoResponse.from(todoRepository.save(todo));
    }

    // 삭제
    public void delete(Long id) {
        Todo todo = getTodoOrThrow(id);
        todoRepository.delete(todo);
    }

    // 존재하지 않으면 예외 발생
    private Todo getTodoOrThrow(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }
}
