package com.nguyencaobach.todoapp.service;

import com.nguyencaobach.todoapp.controller.dto.UserDTO;
import com.nguyencaobach.todoapp.entity.TodoEntity;
import com.nguyencaobach.todoapp.entity.UserEntity;
import com.nguyencaobach.todoapp.exception.TaskCustomException;
import com.nguyencaobach.todoapp.mapper.ModelUtilMapper;
import com.nguyencaobach.todoapp.payload.request.TodoRequest;
import com.nguyencaobach.todoapp.payload.response.TodoResponse;
import com.nguyencaobach.todoapp.repository.TodoRepository;
import com.nguyencaobach.todoapp.repository.UserRepository;
import com.nguyencaobach.todoapp.service.imp.TodoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService implements TodoServiceImp {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<TodoResponse> findAll() {
        List<TodoEntity> todoList = todoRepository.findAll();
        List<TodoResponse> todoResponseList = new ArrayList<>();

        for (TodoEntity data : todoList ){
            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setId(data.getId());
            todoResponse.setTask(data.getTask());
            todoResponse.setDescription(data.getDescription());
            todoResponse.setStatus(data.getStatus());
            todoResponse.setUser(ModelUtilMapper.map(data.getUser(), UserDTO.class));

            todoResponseList.add(todoResponse);
        }

        return todoResponseList;
    }

    @Override
    public List<TodoResponse> getTaskByUserId(int id) {
        List<TodoEntity> todoList = todoRepository.findByUserId(id);
        if (todoList.isEmpty()) {
            throw new TaskCustomException("No tasks found for userId: " + id);
        }
        List<TodoResponse> todoResponseList = new ArrayList<>();
        for (TodoEntity data : todoList ){
            TodoResponse todoResponse = new TodoResponse();
            todoResponse.setId(data.getId());
            todoResponse.setTask(data.getTask());
            todoResponse.setDescription(data.getDescription());
            todoResponse.setStatus(data.getStatus());
            todoResponse.setUser(ModelUtilMapper.map(data.getUser(), UserDTO.class));

            todoResponseList.add(todoResponse);
        }
        return todoResponseList;
    }

    @Override
    public boolean addTask(TodoRequest todoRequest) {
        boolean isSuccess;
        try {
            TodoEntity todoEntity = new TodoEntity();
            todoEntity.setTask(todoRequest.getTask());
            todoEntity.setDescription(todoRequest.getDescription());
            todoEntity.setStatus(todoRequest.getStatus());
            UserEntity userName = userRepository.findById(todoRequest.getUserId())
                    .orElseThrow(() -> new TaskCustomException("User not found: " + todoRequest.getUserId()));
            todoEntity.setUser(userName);
            todoRepository.save(todoEntity);
            isSuccess = true;
        } catch (Exception e) {
            throw new TaskCustomException("Error addTask " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public boolean updateTask(Integer taskId, TodoRequest todoRequest) {
        boolean isSuccess;
        TodoEntity updateTodo = todoRepository.findById(taskId)
                .orElseThrow(() -> new TaskCustomException("Todo not found with id: " + taskId));
        updateTodo.setTask(todoRequest.getTask());
        updateTodo.setDescription(todoRequest.getDescription());
        updateTodo.setStatus(todoRequest.getStatus());
        UserEntity userName = userRepository.findById(todoRequest.getUserId())
                .orElseThrow(() -> new TaskCustomException("User not found: " + todoRequest.getUserId()));
        updateTodo.setUser(userName);
        todoRepository.save(updateTodo);
        isSuccess = true;

        return isSuccess;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isSuccess;
        TodoEntity deleteTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TaskCustomException("Todo not found with id: " + id));
        todoRepository.deleteById(deleteTodo.getId());
        isSuccess = true;
        return isSuccess;
    }
}
