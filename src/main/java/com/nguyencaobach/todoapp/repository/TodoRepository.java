package com.nguyencaobach.todoapp.repository;

import com.nguyencaobach.todoapp.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    List<TodoEntity> findByUserId(int id);

}
