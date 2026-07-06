package br.com.curriculopro.infra.controller;

import br.com.curriculopro.application.usecases.AutenticarUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AutenticarUsuario useCase;

    @InjectMocks
    private AuthController authController;

    private LoginRequest request;
    private LoginResponse response;

    @BeforeEach
    void setup() {
        request  = new LoginRequest("teste@teste.com", "123456");
        response = new LoginResponse("jwt-token");
    }

    @Test
    void deveRetornarLoginComSucesso() {
        when(useCase.executar(request)).thenReturn(response);

        ResponseEntity<LoginResponse> result = authController.login(request);

        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        assertEquals(response, result.getBody());

        verify(useCase, times(1)).executar(request);
    }
}