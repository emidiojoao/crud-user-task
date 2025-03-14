package com.senai.task.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {

    private Long id;

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String descricao;

    @NotBlank(message = "A data não pode estar vazia.")
    private Date dataAgendamento;

    @Min(value = 1, message = "O valor minímo aceito é 1.")
    @Max(value = 4, message = "O valor máximo aceito é 4.")
    private Integer status;

    @NotBlank(message = "O email não pode estar vazio.")
    private String emailUsuario;
}
