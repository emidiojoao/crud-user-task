package com.senai.task.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.senai.task.enums.Status;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDate;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {

    private Long id;

    @NotBlank(message = "O nome não pode estar nulo.")
    private String nome;

    @NotBlank(message = "A descrição não pode estar nula.")
    private String descricao;

    @NotNull(message = "A data não pode estar nulo.")
    private LocalDate dataAgendamento;

    @NotNull(message = "O status não pode ser nulo.")
    private Status status;

    private String mensagem;

    @NotBlank(message = "O email não pode ser nulo.")
    private String emailUsuario;
}
