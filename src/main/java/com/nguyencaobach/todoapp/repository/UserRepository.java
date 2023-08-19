package com.nguyencaobach.todoapp.repository;

import com.nguyencaobach.todoapp.entity.TodoEntity;
import com.nguyencaobach.todoapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findById(int id);
}
