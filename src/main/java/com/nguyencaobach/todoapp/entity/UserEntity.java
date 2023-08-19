package com.nguyencaobach.todoapp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TodoEntity> todoItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<TodoEntity> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoEntity> todoItems) {
        this.todoItems = todoItems;
    }
}
