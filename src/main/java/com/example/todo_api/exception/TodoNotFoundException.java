package com.example.todo_api.exception;

// 존재하지 않는 Todo 조회 시 발생하는 예외 (404 처리용)
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(Long id) {
        super("해당 할 일을 찾을 수 없습니다. id=" + id);
    }
}
