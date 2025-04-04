package com.senai.task.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotBlank(message = "o campo nome não pode ser nulo!")
    private String nome;

    @Email
    @NotBlank(message = "o email não pode ser nulo!")
    private String email;

    @JsonIgnore
    private Boolean sucesso;

}
