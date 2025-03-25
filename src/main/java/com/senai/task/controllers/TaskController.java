package com.senai.task.controllers;

import com.senai.task.dtos.MensagemDto;
import com.senai.task.dtos.TaskDto;
import com.senai.task.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDto>> obterTarefas(){
        List<TaskDto> lista = service.obterTarefas();

        if(lista.isEmpty()){
            return ResponseEntity.status(404).body(lista);
        } else {
            return ResponseEntity.ok().body(lista);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> obterTarefaPorId(@PathVariable Long id){
        TaskDto taskDto = service.obterTarefaPorID(id);

        if(taskDto.getMensagem() != null){
            return ResponseEntity.status(404).body(taskDto);
        }

        return ResponseEntity.ok().body(taskDto);
    }

    @PostMapping
    public ResponseEntity<MensagemDto> criarTask(@RequestBody @Valid TaskDto taskDto){
        MensagemDto mensagem = service.inserirTarefa(taskDto);

        if(mensagem.getSucesso()){
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemDto> atualizarTask(@PathVariable Long id, @RequestBody @Valid TaskDto taskDto){
        MensagemDto mensagem = service.atualizarTarefa(id, taskDto);

        if(mensagem.getSucesso()){
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(404).body(mensagem);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensagemDto> deletarTask(@PathVariable Long id){
        MensagemDto mensagem = service.excluirTarefa(id);

        if(mensagem.getSucesso()){
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(404).body(mensagem);
        }
    }
}
