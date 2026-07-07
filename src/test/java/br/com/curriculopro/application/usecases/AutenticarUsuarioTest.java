package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.User;
import br.com.curriculopro.infra.controller.LoginRequest;
import br.com.curriculopro.infra.controller.LoginResponse;
import br.com.curriculopro.infra.security.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticarUsuarioTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtProvider;

    @InjectMocks
    private AutenticarUsuario autenticarUsuario;


    @Test
    void deveAutenticarUsuarioComSenhaValida() {

        LoginRequest request = new LoginRequest(
                "teste@email.com",
                "123456"
        );

        User usuario = mock(User.class);

        when(repository.findUserByEmail(request.email()))
                .thenReturn(usuario);

        when(usuario.getPassword())
                .thenReturn("senhaCriptografada");

        when(usuario.getEmail())
                .thenReturn("teste@email.com");

        when(usuario.getName())
                .thenReturn("Usuário Teste");

        when(usuario.getId())
                .thenReturn(1L);

        when(passwordEncoder.matches(
                "123456",
                "senhaCriptografada"))
                .thenReturn(true);

        when(jwtProvider.gerarToken(
                "teste@email.com",
                "Usuário Teste",
                1L))
                .thenReturn("jwt-token");


        LoginResponse response =
                autenticarUsuario.executar(request);


        assertNotNull(response);
        assertEquals("jwt-token", response.token());

        verify(repository)
                .findUserByEmail(request.email());

        verify(jwtProvider)
                .gerarToken(
                        "teste@email.com",
                        "Usuário Teste",
                        1L);
    }


    @Test
    void deveLancarErroQuandoSenhaForInvalida() {

        LoginRequest request = new LoginRequest(
                "teste@email.com",
                "senhaErrada"
        );

        User usuario = mock(User.class);

        when(repository.findUserByEmail(request.email()))
                .thenReturn(usuario);

        when(usuario.getPassword())
                .thenReturn("senhaCriptografada");

        when(passwordEncoder.matches(
                "senhaErrada",
                "senhaCriptografada"))
                .thenReturn(false);


        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> autenticarUsuario.executar(request)
                );


        assertEquals(
                "Senha inválida",
                exception.getMessage()
        );


        verify(jwtProvider, never())
                .gerarToken(any(), any(), any());
    }
}