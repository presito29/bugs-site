package com.plannerapp.repo;


import com.plannerapp.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findAllByUser_IdNot(Long id);
}
