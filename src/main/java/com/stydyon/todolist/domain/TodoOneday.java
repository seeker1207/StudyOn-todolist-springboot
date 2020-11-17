package com.stydyon.todolist.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class TodoOneday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "todoOneday", cascade = CascadeType.ALL)
    private List<Todo> todos = new ArrayList<>();

    @Column(nullable = false)
    private LocalDate todoDate;

    @Enumerated(EnumType.STRING)
    private AchievmentType achievement;

    @Builder
    public TodoOneday(Long id, Long userId, LocalDate todoDate, List<Todo> todos) {
        this.id = id;
        this.userId = userId;
        if(todos != null){
            for(Todo todo : todos){
                this.addTodo(todo);
            }
        }

        this.todoDate = todoDate;
    }

    public void addTodo(Todo todo) {
        todo.setTodoOneday(this);
        this.todos.add(todo);
    }

    public void checkAchieve(String achieveString) {
        this.achievement = AchievmentType.valueOf(achieveString.toUpperCase());
    }

}
