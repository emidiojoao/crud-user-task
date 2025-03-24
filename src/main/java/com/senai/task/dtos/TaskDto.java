package com.senai.task.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;
@Data
public class TaskDto {

    private Long id;

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "A descrição não pode estar vazia.")
    private String descricao;

    @NotNull(message = "A data não pode estar vazia.")
    @Future(message = "A data deve ser no futuro.")
    private LocalDate data;

    @Min(value = 1, message = "O valor minímo aceito é 1.")
    @Max(value = 4, message = "O valor máximo aceito é 4.")
    private Integer status;

    @NotBlank(message = "O email não pode ser vazio.")
    private String emailUsuario;
}
