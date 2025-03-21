package com.senai.task.repositories;

import com.senai.task.models.TaskModel;
import com.senai.task.models.UserModel;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    boolean existsByUserEmail(String email);

    Optional<TaskModel> findByDateAndEmail(Date dataDeAgendamento, String email);
}
