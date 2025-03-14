package com.senai.task.controllers;

import com.senai.task.dtos.UserDto;
import com.senai.task.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired

    @GetMapping
    public ResponseEntity<Object> obterUsuario(){
        List<UserDto> lista = new ArrayList<>();

        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<Object> criarUsuario(@RequestBody @Valid UserDto user){
        UserDto userDto = new UserDto();

        return ResponseEntity.ok().body(userDto);
    }

    @PutMapping("/{email}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable String email, @RequestBody @Valid UserDto user){

        return ResponseEntity.ok().body(new Object());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Object> excluirUsuario(@PathVariable String email){

        return ResponseEntity.ok().body(new Object());
    }
}
