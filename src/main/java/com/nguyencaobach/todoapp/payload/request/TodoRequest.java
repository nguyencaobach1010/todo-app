package com.nguyencaobach.todoapp.payload.request;

import com.nguyencaobach.todoapp.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequest {
    private String task;
    private String description;
    private LocalDateTime createdAt;
    private Status status;
    private int userId;
}
