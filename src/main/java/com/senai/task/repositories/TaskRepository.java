package com.senai.task.repositories;
import com.senai.task.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    boolean existsByUsuarioModel_Email(String email);

    Optional<TaskModel> findByDataAgendamentoAndUsuarioModel_Email(LocalDate dataAgendamento, String emailUsuario);
}
