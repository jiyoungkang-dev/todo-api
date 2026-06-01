package com.example.todo_api.dto;

import jakarta.validation.constraints.NotBlank;

// 할 일 생성/수정 요청 DTO
public class TodoRequest {

    @NotBlank(message = "제목은 필수입니다")
    private String title;

    private boolean completed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
