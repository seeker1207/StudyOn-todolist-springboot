package com.stydyon.todolist.web.dto;

import com.stydyon.todolist.domain.AchievmentType;
import com.stydyon.todolist.domain.Todo;
import com.stydyon.todolist.domain.TodoOneday;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TodoOnedayResponseDto {

    private Long id;
    private Long userId;
    private String todoDate;
    private AchievmentType achievment;
    private List<TodoResponseDto> todos = new ArrayList<>();

    public TodoOnedayResponseDto(TodoOneday entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.todoDate = entity.getTodoDate().toString();
        this.achievment = entity.getAchievement();
        for (Todo todo:entity.getTodos()){
            this.todos.add(new TodoResponseDto(todo));
        }

    }

}
