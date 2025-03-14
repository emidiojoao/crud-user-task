package com.senai.task.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;


}
