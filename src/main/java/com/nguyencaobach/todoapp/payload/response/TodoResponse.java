package com.nguyencaobach.todoapp.payload.response;

import com.nguyencaobach.todoapp.dto.UserDTO;
import com.nguyencaobach.todoapp.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private int id;
    private String task;
    private String description;
    private Status status;
    private UserDTO user;
}
