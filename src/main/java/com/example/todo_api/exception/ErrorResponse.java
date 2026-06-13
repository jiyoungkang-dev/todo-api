package com.example.todo_api.exception;

// 에러 응답 형식 (schedule-api와 동일한 형식)
public record ErrorResponse(
        String message
) {
}
