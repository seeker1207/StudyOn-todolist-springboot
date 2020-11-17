package com.stydyon.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "todoOneday_id")
    private TodoOneday todoOneday;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private boolean isComplete;

    @Builder
    public Todo(Long id, TodoOneday todoOneday, String desc, boolean isComplete) {
        this.id = id;
        this.todoOneday = todoOneday;
        this.desc = desc;
        this.isComplete = isComplete;
    }

    public void setTodoOneday(TodoOneday todoOneday) {
        this.todoOneday = todoOneday;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", todoOneday=" + todoOneday +
                ", desc='" + desc + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
