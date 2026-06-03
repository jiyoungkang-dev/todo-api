package com.example.todo_api.service;

import com.example.todo_api.dto.TodoRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.entity.Todo;
import com.example.todo_api.exception.TodoNotFoundException;
import com.example.todo_api.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    // 목록 조회 (completed 필터 + 페이징)
    // completed가 null이면 전체, 값이 있으면 해당 상태만 조회
    public Page<TodoResponse> findAll(Boolean completed, Pageable pageable) {
        Page<Todo> todos = (completed == null)
                ? todoRepository.findAll(pageable)
                : todoRepository.findByCompleted(completed, pageable);
        return todos.map(TodoResponse::from);
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
