package com.senai.task.services;

import com.senai.task.dtos.MensagemDto;
import com.senai.task.dtos.TaskDto;
import com.senai.task.models.TaskModel;
import com.senai.task.models.UserModel;
import com.senai.task.repositories.TaskRepository;
import com.senai.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    //--Listar Tarefa
    public List<TaskDto> obterTarefas(){
        List<TaskDto> listaTaskDto = new ArrayList<>();
        List<TaskModel> listaTaskModel = taskRepository.findAll();

        for (TaskModel taskModel :listaTaskModel){
            TaskDto taskDto = new TaskDto();
            taskDto.setId(taskModel.getId());
            taskDto.setNome(taskModel.getNome());
            taskDto.setDescricao(taskModel.getDescricao());
            taskDto.setDataAgendamento(taskModel.getDataAgendamento());
            taskDto.setStatus(taskModel.getStatus());
            taskDto.setEmailUsuario(taskModel.getUsuarioModel().getEmail());
            listaTaskDto.add(taskDto);
        }
        return listaTaskDto;
    }

    public TaskDto obterTarefaPorID(Long id){
        TaskDto taskDto = new TaskDto();

        Optional<TaskModel> buscaTarefa = taskRepository.findById(id);
        if(buscaTarefa.isEmpty()){
            taskDto.setMensagem("[ERRO] - Usuário não encontrado");
            return taskDto;
        }

        TaskModel taskModel = buscaTarefa.get();
        taskDto.setNome(taskModel.getNome());
        taskDto.setDescricao(taskModel.getDescricao());
        taskDto.setDataAgendamento(taskModel.getDataAgendamento());
        taskDto.setStatus(taskModel.getStatus());
        taskDto.setEmailUsuario(taskModel.getUsuarioModel().getEmail());

        return taskDto;
    }

    //--Inserir Tarefa
    public MensagemDto inserirTarefa(TaskDto taskDto){
        TaskModel taskModel = new TaskModel();
        MensagemDto mensagem = new MensagemDto();

        Optional<UserModel> validarUsuarioExistente = userRepository.findByEmail(taskDto.getEmailUsuario());
        if(validarUsuarioExistente.isEmpty()){
            mensagem.setMensagem("[ERRO] - Usuário não encontrado!");
            mensagem.setSucesso(false);
            return mensagem;
        }

        Optional<TaskModel> validarDataExistente = taskRepository.findByDataAgendamentoAndUsuarioModel_Email(taskDto.getDataAgendamento(), taskDto.getEmailUsuario());
        if(validarDataExistente.isPresent()){
            mensagem.setMensagem("[ERRO] - Esse usuário ja tem uma tarefa agendada para essa data!");
            mensagem.setSucesso(false);
            return mensagem;
        }

        taskModel.setNome(taskDto.getNome());
        taskModel.setDescricao(taskDto.getDescricao());
        taskModel.setDataAgendamento(taskDto.getDataAgendamento());
        taskModel.setStatus(taskDto.getStatus());
        taskModel.setUsuarioModel(validarUsuarioExistente.get());
        taskRepository.save(taskModel);

        mensagem.setMensagem("Tarefa inserida com sucesso!");
        mensagem.setSucesso(true);
        return mensagem;
    }

    //--AtualizarTarefa
    public MensagemDto atualizarTarefa(Long id, TaskDto taskDto){
        MensagemDto mensagem = new MensagemDto();

        Optional<TaskModel> obterTarefaPorId = taskRepository.findById(id);
        if(obterTarefaPorId.isEmpty()){
            mensagem.setMensagem("[ERRO] - Tarefa não encontrada!");
            mensagem.setSucesso(false);
            return mensagem;
        }

        Optional<UserModel> validarUsuarioExistente = userRepository.findByEmail(taskDto.getEmailUsuario());
        if(validarUsuarioExistente.isEmpty()){
            mensagem.setMensagem("[ERRO] - Usuário não encotrado!");
            mensagem.setSucesso(false);
            return mensagem;
        }

        Optional<TaskModel> validarData = taskRepository.findByDataAgendamentoAndUsuarioModel_Email(taskDto.getDataAgendamento(), taskDto.getEmailUsuario());
        if(validarData.isPresent() && !validarData.get().getId().equals(id)){
            mensagem.setMensagem("[ERRO] - Esse usuário ja possui uma tarefa para essa data!");
            mensagem.setSucesso(false);
            return mensagem;
        }

        TaskModel taskModel = obterTarefaPorId.get();

        taskModel.setNome(taskDto.getNome());
        taskModel.setDescricao(taskDto.getDescricao());
        taskModel.setDataAgendamento(taskDto.getDataAgendamento());
        taskModel.setStatus(taskDto.getStatus());
        taskModel.setUsuarioModel(validarUsuarioExistente.get());
        taskRepository.save(taskModel);

        mensagem.setMensagem("Tarefa atualizada com sucesso!");
        mensagem.setSucesso(true);
        return mensagem;
    }

    //--Deletar Tarefa
    public MensagemDto excluirTarefa(Long tarefaId){
        MensagemDto mensagem = new MensagemDto();

        Optional<TaskModel> tarefaExistente = taskRepository.findById(tarefaId);
        if(tarefaExistente.isEmpty()){
            mensagem.setMensagem("[ERRO] - Tarefa não encontrada");
            mensagem.setSucesso(false);
            return mensagem;
        }

        taskRepository.delete(tarefaExistente.get());
        mensagem.setMensagem("Tarefa removida com sucesso!");
        mensagem.setSucesso(true);
        return mensagem;

    }

    //--Buscar tarefas existentes
    public Optional<TaskModel> buscarTarefaPorDataEmail(LocalDate data, String email){
        return taskRepository.findByDataAgendamentoAndUsuarioModel_Email(data, email);
    }


}
