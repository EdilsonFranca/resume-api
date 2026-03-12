package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.User;
import br.com.curriculopro.infra.controller.LoginRequest;
import br.com.curriculopro.infra.controller.LoginResponse;
import br.com.curriculopro.infra.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AutenticarUsuario {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtProvider;

    public AutenticarUsuario(UserRepository repository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtProvider) {
        this.repository      = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider     = jwtProvider;
    }

    public LoginResponse executar(LoginRequest request) {

        User usuario = repository.findUserByEmail(request.email());

        if (!passwordEncoder.matches(request.password().trim(), usuario.getPassword().trim())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtProvider.gerarToken(usuario.getEmail(),usuario.getName(),usuario.getId());
        return new LoginResponse(token);
    }
}