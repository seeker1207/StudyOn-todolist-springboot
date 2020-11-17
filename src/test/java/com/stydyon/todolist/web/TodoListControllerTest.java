package com.stydyon.todolist.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TodoListController.class)
class TodoListControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }

    @Test
    public void TodoListDto가_리턴된다() throws  Exception {
        Long id = 1l;
        Long userId = 1l;
        String desc = "test용";
        LocalDate today = LocalDate.of(2020,11,30);
        boolean isComplete = true;

        mvc.perform(
                get("/todo/dto")
                .param("id", String.valueOf(id))
                .param("userId", String.valueOf(userId))
                .param("desc", desc)
                .param("today", today.toString())
                .param("isComplete", String.valueOf(1))

        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.todayDate").value(today.toString()));
    }


}