package com.nguyencaobach.todoapp.controller;

import com.nguyencaobach.todoapp.payload.request.TodoRequest;
import com.nguyencaobach.todoapp.payload.response.BaseResponse;
import com.nguyencaobach.todoapp.payload.response.TodoResponse;
import com.nguyencaobach.todoapp.repository.TodoRepository;
import com.nguyencaobach.todoapp.service.imp.TodoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoServiceImp todoServiceImp;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<?> findAll() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Success!!!!");
        response.setData(todoServiceImp.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable int id) {
        try {
            BaseResponse response = new BaseResponse();
            List<TodoResponse> todoList = todoServiceImp.getTaskByUserId(id);
            if (todoList.isEmpty()) {
                response.setStatusCode(204); // no content
                response.setMessage("No tasks found for user Id: " + id);
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }else {
                response.setStatusCode(200);
                response.setMessage("Success!!!!");
                response.setData(todoList);

            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            BaseResponse response = new BaseResponse();
                response.setStatusCode(404);
                response.setMessage("An error occurred.");
                response.setData(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
  }
    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TodoRequest todoRequest, BindingResult errors) {
        if(errors.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BaseResponse response = new BaseResponse();
        response.setStatusCode(201);
        response.setMessage("Create!!!!");
        response.setData(todoServiceImp.addTask(todoRequest));
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") Integer todoId,
                                        @RequestBody TodoRequest todoEntity, BindingResult errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BaseResponse response = new BaseResponse();
        response.setStatusCode(202);
        response.setMessage("Update Sucess!!!!");
        response.setData(todoServiceImp.updateTask(todoId, todoEntity));

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") int taskId) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Delete Sucess!!!!");
        response.setData(todoServiceImp.deleteById(taskId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
