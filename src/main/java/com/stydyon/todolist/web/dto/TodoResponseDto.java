package com.stydyon.todolist.web.dto;

import com.stydyon.todolist.domain.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoResponseDto {
    private Long id;
    private String desc;
    private boolean isComplete;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.desc = todo.getDescription();
        this.isComplete = todo.isComplete();
    }
}
