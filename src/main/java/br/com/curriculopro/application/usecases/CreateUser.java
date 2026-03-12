package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.User;

public class CreateUser {
    private final UserRepository repositorio;

    public CreateUser(UserRepository repositorio) {
        this.repositorio = repositorio;
    }

    public User create(User user) {
        return repositorio.create(user);
    }
}
