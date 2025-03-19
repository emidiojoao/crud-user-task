package com.senai.task.services;

import com.senai.task.dtos.MensagemDto;
import com.senai.task.dtos.UserDto;
import com.senai.task.models.UserModel;
import com.senai.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    //--Listar todos os usuários
    public List<UserDto> obterUsuarios(){
        List<UserDto> listaDto = new ArrayList<>();
        List<UserModel> listaModel = repository.findAll();

        for(UserModel userModel : listaModel){
            UserDto user = new UserDto();
            user.setNome(userModel.getNome());
            user.setEmail(userModel.getEmail());
            listaDto.add(user);
        }
        return listaDto;
    }

    //--Criar um novo usuário
    public MensagemDto criarUsuario(UserDto dados){
        MensagemDto mensagem = new MensagemDto();
        UserModel userModel = new UserModel();
        Optional<UserModel> obterEmail = repository.findByEmail(dados.getEmail());

        if(obterEmail.isPresent()){
            mensagem.setMensagem("[ERRO] - Já existe um usuário com esse login");
            mensagem.setSucesso(false);
            return mensagem;
        }

        userModel.setNome(dados.getNome());
        userModel.setEmail(dados.getEmail());
        repository.save(userModel);
        mensagem.setMensagem("Usuário cadastrado!");
        mensagem.setSucesso(true);
        return mensagem;
    }

    //--Atualizar um usuário pelo email
    public MensagemDto atualizarUsuario(String email, UserDto userDto){
        MensagemDto mensagem = new MensagemDto();

        Optional<UserModel> usuarioPesquisado = repository.findByEmail(email);
        if (usuarioPesquisado.isEmpty()){
            mensagem.setMensagem("[ERRO] - Usuário não encontrado!");
            mensagem.setSucesso(false);
            return mensagem;
        }

        UserModel userModel = usuarioPesquisado.get();
        userModel.setNome(userDto.getNome());
        userModel.setEmail(userDto.getEmail());
        repository.save(userModel);
        mensagem.setMensagem("Usuário atualizado!");
        mensagem.setSucesso(true);

        return mensagem;
    }

    //--Obter usuário por email
    public UserDto obterUsuario(String email){
        UserDto userDto = new UserDto();

        Optional<UserModel> usuarioPesquisado = repository.findByEmail(email);
        if (usuarioPesquisado.isPresent()){
            UserModel userModel = usuarioPesquisado.get();
            userDto.setNome(userModel.getNome());
            userDto.setEmail(userModel.getEmail());
            userDto.setSucesso(true);
            return userDto;
        }

        userDto.setSucesso(false);
        return userDto;
    }

    //--Excluir usuário por email
    public MensagemDto excluirUsuario(String email){
        MensagemDto mensagem = new MensagemDto();

        Optional<UserModel> usuarioPesquisado = repository.findByEmail(email);
        if (usuarioPesquisado.isEmpty()){
            mensagem.setMensagem("Usuário não encontrado");
            mensagem.setSucesso(false);
            return mensagem;
        }

        repository.delete(usuarioPesquisado.get());
        mensagem.setMensagem("Usuário deletado");
        mensagem.setSucesso(true);
        return mensagem;
    }
}
