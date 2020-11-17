package com.stydyon.todolist.web;

import com.stydyon.todolist.service.TodoOnedayService;
import com.stydyon.todolist.web.dto.TodoOnedayResponseDto;
import com.stydyon.todolist.web.dto.TodoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TodoListController {

    final private TodoOnedayService todoOnedayService;

    @GetMapping("/todo")
    public TodoOnedayResponseDto getTodoOneday(@RequestParam("userId") Long userId,
                                               @RequestParam("todoDate") String todoDate) {

        return todoOnedayService.getTodoByDate(userId, LocalDate.parse(todoDate,DateTimeFormatter.ISO_DATE));
    }

    @PostMapping("/todo")
    public Long registerTodoOneday(@RequestBody TodoRequestDto todoRequestDto) {

        return todoOnedayService.resisterTodo(todoRequestDto);
    }

    @GetMapping("/todo-oneday")
    public List<TodoOnedayResponseDto> getTodoOnedaysByMonth(@RequestParam("month") int month,
                                                             @RequestParam("userId") Long userId) {

        return todoOnedayService.getTodoOneDayByMonth(month, userId);
    }
    @PutMapping("/todo-oneday/{todoOnedayId}")
    public TodoOnedayResponseDto checkAchieve(@PathVariable("todoOnedayId") Long todoOnedayId,
                                              @RequestParam("achieve") String achievement){

        return todoOnedayService.checkAchieve(todoOnedayId, achievement);
    }

    @DeleteMapping("/todo/{todoId}")
    public boolean deleteTodo(@PathVariable("todoId") Long todoId) {

        return todoOnedayService.deleteTodo(todoId);
    }
}