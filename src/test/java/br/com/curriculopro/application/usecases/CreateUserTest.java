package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    @Mock
    private UserRepository repositorio;

    @InjectMocks
    private CreateUser createUser;


    @Test
    void deveCriarUsuarioComSucesso() {

        User user = mock(User.class);
        User usuarioSalvo = mock(User.class);


        when(repositorio.create(user))
                .thenReturn(usuarioSalvo);


        User resultado = createUser.create(user);


        verify(repositorio)
                .create(user);

        assertSame(usuarioSalvo, resultado);
    }
}