package com.example.todo_api.dto;

import com.example.todo_api.entity.Todo;

// 할 일 응답 DTO (Entity를 직접 노출하지 않기 위해 변환하여 사용)
public class TodoResponse {

    private Long id;
    private String title;
    private boolean completed;

    public TodoResponse(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // Entity -> DTO 변환
    public static TodoResponse from(Todo todo) {
        return new TodoResponse(todo.getId(), todo.getTitle(), todo.isCompleted());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
