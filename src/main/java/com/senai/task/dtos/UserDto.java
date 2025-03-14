package com.senai.task.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "o campo nome não pode ser vazio!")
    private String nome;

    @Email
    @NotBlank(message = "o email não pode ser vazio!")
    private String email;

}
