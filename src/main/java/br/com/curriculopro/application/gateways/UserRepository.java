package br.com.curriculopro.application.gateways;

import br.com.curriculopro.domain.entities.User;

public interface UserRepository {
    User findUserByEmail(String username);
    User create(User resume);
}






