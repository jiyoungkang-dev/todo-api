package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 할 일 REST API 엔드포인트
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 할 일 생성
    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(request));
    }

    // 목록 조회 (completed 필터 + 페이징)
    // 예: /api/todos?completed=true&page=0&size=10&sort=id,desc
    @GetMapping
    public Page<TodoResponse> findAll(
            @RequestParam(required = false) Boolean completed,
            Pageable pageable) {
        return todoService.findAll(completed, pageable);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public TodoResponse findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    // 수정
    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        return todoService.update(id, request);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
