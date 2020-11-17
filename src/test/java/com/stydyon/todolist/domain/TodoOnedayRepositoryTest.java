package com.stydyon.todolist.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TodoOnedayRepositoryTest {

    @Autowired
    TodoOnedayRepository todoOnedayRepository;
    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    public void setUp() {


    }

    @Test
    @Rollback(value = false)
    public void 하루_일정_저장하기(){

        Todo todo1 = Todo.builder()
                .desc("자격증 공부하기")
                .isComplete(true)
                .build();
        Todo todo2 = Todo.builder()
                .desc("면접 준비하기")
                .isComplete(false)
                .build();

        TodoOneday todoOneday = TodoOneday.builder()
                .todoDate(LocalDate.of(2020, 10, 30))
                .userId(10l)
                .build();

        todo1.setTodoOneday(todoOneday);
        todo2.setTodoOneday(todoOneday);
//        todoOnedayRepository.save(todoOneday);
        todoRepository.save(todo1);
        todoRepository.save(todo2);

        List<TodoOneday> all = todoOnedayRepository.findAll();
        List<Todo> todos = todoRepository.findAll();
        assertEquals(todoOneday.getUserId(), all.get(0).getUserId());
//        assertEquals(todo.getDesc(), all.get(0).getTodos().get(0).getDesc());
        assertEquals(todoOneday.getTodoDate(), todos.get(0).getTodoOneday().getTodoDate());

    }

    @Test
//    @Rollback(value = false)
    public void 하루_일정_조회하기() {
        Todo todo1 = Todo.builder().desc("자격증 공부하기").isComplete(false).build();
        Todo todo2 = Todo.builder().desc("면접 준비하기").isComplete(false).build();
//
//        List<Todo> todos = new ArrayList<>();
//        todos.add(todo1);
//        todos.add(todo2);
//
//        TodoOneday todoOneday = TodoOneday.builder()
//                .todoDate(LocalDate.of(2020, 10, 30))
//                .userId(10l)
////                .todos(todos)
//                .build();
//        todo1.setTodoOneday(todoOneday);
//        todo2.setTodoOneday(todoOneday);
//
//        todoRepository.saveAll(todos);
////        todoOnedayRepository.save(todoOneday);
//        System.out.println(todoOneday.getId());
        TodoOneday todooneday = todoOnedayRepository.findTodoOnedayWithUserIdAndTodoDate(10l,
                LocalDate.of(2020,10,30));

        assertEquals(todooneday.getTodos().get(0).getDesc(), todo1.getDesc());
        assertEquals(todooneday.getTodos().get(1).getDesc(), todo2.getDesc());


    }

}