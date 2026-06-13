package com.example.todo_api.controller;

import com.example.todo_api.dto.TodoRequest;
import com.example.todo_api.dto.TodoResponse;
import com.example.todo_api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 할 일 REST API 엔드포인트
@Tag(name = "Todo", description = "할 일 관리 API")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // 할 일 생성
    @Operation(summary = "할 일 생성", description = "새로운 할 일을 등록한다")
    @PostMapping
    public ResponseEntity<TodoResponse> create(@Valid @RequestBody TodoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(request));
    }

    // 목록 조회 (completed + keyword 필터 + 페이징)
    // 예: /api/todos?completed=true&keyword=회의&page=0&size=10&sort=id,desc
    @Operation(summary = "할 일 목록 조회", description = "completed/keyword 필터와 페이징을 지원한다")
    @GetMapping
    public Page<TodoResponse> findAll(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String keyword,
            Pageable pageable) {
        return todoService.findAll(completed, keyword, pageable);
    }

    // 단건 조회
    @Operation(summary = "할 일 단건 조회", description = "ID로 할 일 하나를 조회한다")
    @GetMapping("/{id}")
    public TodoResponse findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    // 수정
    @Operation(summary = "할 일 수정", description = "ID에 해당하는 할 일을 수정한다")
    @PutMapping("/{id}")
    public TodoResponse update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        return todoService.update(id, request);
    }

    // 삭제
    @Operation(summary = "할 일 삭제", description = "ID에 해당하는 할 일을 삭제한다")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
