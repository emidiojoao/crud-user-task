package com.senai.task.models;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "tarefa")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_de_agendamento", nullable = false)
    private LocalDate data;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UserModel usuarioModel;


}
