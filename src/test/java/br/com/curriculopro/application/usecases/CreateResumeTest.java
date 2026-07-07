package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.Resume;
import br.com.curriculopro.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateResumeTest {

    @Mock
    private ResumeRepository repositorio;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateResume createResume;


    @Test
    void deveCriarCurriculoAssociandoUsuario() {

        String email = "teste@email.com";

        User user = mock(User.class);
        Resume resume = mock(Resume.class);
        Resume resumeSalvo = mock(Resume.class);


        when(userRepository.findUserByEmail(email))
                .thenReturn(user);

        when(repositorio.create(resume))
                .thenReturn(resumeSalvo);


        Resume resultado =
                createResume.create(email, resume);


        verify(userRepository)
                .findUserByEmail(email);

        verify(resume)
                .setUser(user);

        verify(repositorio)
                .create(resume);


        assertSame(resumeSalvo, resultado);
    }
}