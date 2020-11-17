package com.stydyon.todolist.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TodoOnedayRepository extends JpaRepository<TodoOneday, Long> {
    @Query(value = "select DISTINCT c from TodoOneday c left join fetch c.todos where c.userId = ?1 and c.todoDate = ?2")
    TodoOneday findTodoOnedayWithUserIdAndTodoDate(Long userId, LocalDate todoDate);

    TodoOneday findTodoOnedayByUserIdAndTodoDate(Long userId, LocalDate todoDate);

    @Query(value = "select DISTINCT id, achievement, todo_date, user_id from todo_oneday t where MONTH(t.todo_date) = ?1 ", nativeQuery = true)
    List<TodoOneday> findTodoOnedaysByMonth(int month);
}
