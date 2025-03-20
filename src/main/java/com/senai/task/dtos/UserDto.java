package com.senai.task.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "o campo nome não pode ser vazio!")
    private String nome;

    @Email
    @NotBlank(message = "o email não pode ser vazio!")
    private String email;

    @JsonIgnore
    private Boolean sucesso;

}
