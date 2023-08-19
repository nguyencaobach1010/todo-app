package com.nguyencaobach.todoapp.service.imp;

import com.nguyencaobach.todoapp.entity.TodoEntity;
import com.nguyencaobach.todoapp.payload.request.TodoRequest;
import com.nguyencaobach.todoapp.payload.response.TodoResponse;

import java.util.List;
import java.util.Optional;

public interface TodoServiceImp {
    List<TodoResponse> findAll();
    List<TodoResponse> getTaskByUserId(int id);
    boolean addTask(TodoRequest userRequest);
    boolean updateTask(Integer userId, TodoRequest updatedUser);
    boolean deleteById(int id);

}
