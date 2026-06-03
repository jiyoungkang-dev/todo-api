package com.example.todo_api.repository;

import com.example.todo_api.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// 할 일 DB 접근 (JPA)
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // completed 값으로 필터링 + 페이징 (메서드 이름만으로 쿼리 자동 생성)
    Page<Todo> findByCompleted(boolean completed, Pageable pageable);
}
