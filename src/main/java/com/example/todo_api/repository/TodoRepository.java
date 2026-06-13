package com.example.todo_api.repository;

import com.example.todo_api.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 할 일 DB 접근 (JPA)
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // completed 값으로 필터링 + 페이징 (메서드 이름만으로 쿼리 자동 생성)
    Page<Todo> findByCompleted(boolean completed, Pageable pageable);

    /**
     * 목록 검색. completed / keyword 는 선택적 필터 (null 이면 조건 무시).
     * 둘 다 들어오면 AND 로 결합한다. keyword 는 title 부분 일치 검색.
     */
    @Query("""
            select t from Todo t
            where (:completed is null or t.completed = :completed)
              and (:keyword is null or t.title like concat('%', :keyword, '%'))
            """)
    Page<Todo> search(@Param("completed") Boolean completed,
                      @Param("keyword") String keyword,
                      Pageable pageable);
}
