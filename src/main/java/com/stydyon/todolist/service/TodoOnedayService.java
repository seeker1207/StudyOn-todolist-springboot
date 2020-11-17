package com.stydyon.todolist.service;

import com.stydyon.todolist.domain.Todo;
import com.stydyon.todolist.domain.TodoOneday;
import com.stydyon.todolist.domain.TodoOnedayRepository;
import com.stydyon.todolist.domain.TodoRepository;
import com.stydyon.todolist.web.dto.TodoOnedayResponseDto;
import com.stydyon.todolist.web.dto.TodoRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoOnedayService {
    final TodoOnedayRepository todoOnedayRepository;
    final TodoRepository todoRepository;

    @Transactional
    public Long resisterTodo(TodoRequestDto todoRequestDto) {
        LocalDate todoDate = LocalDate.parse(todoRequestDto.getTodoDate(), DateTimeFormatter.ISO_DATE);
        Optional<TodoOneday> opt = Optional.ofNullable(
                todoOnedayRepository.findTodoOnedayByUserIdAndTodoDate(todoRequestDto.getUserId(), todoDate));
        Todo todo = todoRequestDto.toDomain();

        opt.ifPresent(todoOneday -> todo.setTodoOneday(todoOneday));
        todoRepository.save(todo);

        return todo.getId();
    }


    @Transactional
    public TodoOnedayResponseDto getTodoByDate(Long userId, LocalDate todoDate) {

        TodoOneday resultTodoOneday = todoOnedayRepository.findTodoOnedayWithUserIdAndTodoDate(userId, todoDate);

        return new TodoOnedayResponseDto(resultTodoOneday);
    }

    @Transactional
    public List<TodoOnedayResponseDto> getTodoOneDayByMonth(int month) {

        List<TodoOnedayResponseDto> todoOnedayResponseDtos = new ArrayList<>();
        List<TodoOneday> resultTodoOneday = todoOnedayRepository.findTodoOnedaysByMonth(month);

        for (TodoOneday todoOneday : resultTodoOneday) {
            todoOnedayResponseDtos.add(new TodoOnedayResponseDto(todoOneday));
        }

        return todoOnedayResponseDtos;
    }
    @Transactional
    public TodoOnedayResponseDto checkAchieve(Long todoOnedayId, String AchieveString){

        Optional<TodoOneday> opt = todoOnedayRepository.findById(todoOnedayId);

        opt.ifPresent(todoOneday -> todoOneday.checkAchieve(AchieveString));

        return new TodoOnedayResponseDto(opt.get());
    }

    @Transactional
    public boolean deleteTodo(Long todoId) {
        Optional<Todo> opt = todoRepository.findById(todoId);

        if(opt.isEmpty()) return false;

        opt.ifPresent(todo -> todoRepository.delete(todo));

        return true;
    }
}
