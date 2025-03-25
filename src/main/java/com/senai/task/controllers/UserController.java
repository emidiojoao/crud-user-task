package com.senai.task.controllers;

import com.senai.task.dtos.MensagemDto;
import com.senai.task.dtos.UserDto;
import com.senai.task.models.UserModel;
import com.senai.task.services.UserService;
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
    UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> obterUsuario(){
        List<UserDto> lista = service.obterUsuarios();

        if(lista.isEmpty()){
            return ResponseEntity.status(404).body(lista);
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }
    @GetMapping("/{email}")
    public ResponseEntity<UserDto> obterUsuarioPorEmail(@PathVariable String email){
        UserDto userDto = service.obterUsuario(email);

        if(userDto.getSucesso()){
            return ResponseEntity.ok().body(userDto);
        } else {
            return ResponseEntity.status(404).body(userDto);
        }
    }

    @PostMapping
    public ResponseEntity<MensagemDto> criarUsuario(@RequestBody @Valid UserDto user){
       MensagemDto mensagem = service.criarUsuario(user);

       if(mensagem.getSucesso()){
           return ResponseEntity.ok().body(mensagem);
       } else {
           return ResponseEntity.status(409).body(mensagem);
       }
    }

    @PutMapping("/{email}")
    public ResponseEntity<MensagemDto> atualizarUsuario(@PathVariable String email, @RequestBody @Valid UserDto user){
        MensagemDto mensagem = service.atualizarUsuario(email, user);

        if(mensagem.getSucesso()){
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<MensagemDto> excluirUsuario(@PathVariable String email){
        MensagemDto mensagem = service.excluirUsuario(email);

        if(mensagem.getSucesso()){
            return ResponseEntity.ok().body(mensagem);
        }
        if(mensagem.getMensagem().equals("Usuário vinculado a uma tarefa!")){
            return ResponseEntity.status(409).body(mensagem);
        }
        return ResponseEntity.status(404).body(mensagem);
    }
}
