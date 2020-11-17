package com.stydyon.todolist.web.dto;

import com.stydyon.todolist.domain.Todo;
import com.stydyon.todolist.domain.TodoOneday;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class TodoRequestDto {

    private Long userId;
    private String desc;
    private String todoDate;
    private boolean isComplete;

    public Todo toDomain(){
        TodoOneday todoOneday = TodoOneday.builder()
                .userId(this.userId)
                .todoDate(LocalDate.parse(this.todoDate, DateTimeFormatter.ISO_DATE))
                .build();

        Todo todo = Todo.builder()
                .isComplete(this.isComplete)
                .desc(this.desc)
                .todoOneday(todoOneday)
                .build();

        return todo;
    }

}
