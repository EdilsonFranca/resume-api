package br.com.curriculopro.infra.gateways;

import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.User;
import br.com.curriculopro.infra.persistence.RepositoryUsuario;
import br.com.curriculopro.infra.persistence.UserEntity;

import java.util.Optional;

public class UserRepositoryJpa implements UserRepository {

    private final RepositoryUsuario repositorio;
    private final UsuarioEntityMapper mapper;

    public UserRepositoryJpa(RepositoryUsuario repositorio, UsuarioEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper      = mapper;
    }

    @Override
    public User findUserByEmail(String username) {
        UserEntity entity = repositorio.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return mapper.toDomain(entity);
    }

    @Override
    public User create(User resume) {
        Optional<UserEntity> optional = repositorio.findByEmail(resume.getEmail());
        UserEntity entity = mapper.toEntity(resume);
        UserEntity save = optional.orElseGet(() -> repositorio.save(entity));
        return mapper.toDomain(save);
    }


}

