package br.com.curriculopro.application.usecases;

import br.com.curriculopro.domain.entities.Resume;
import br.com.curriculopro.application.gateways.ResumeRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindResumeAllByUserTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private FindResumeAllByUser findResumeAllByUser;


    @Test
    void deveBuscarTodosOsCurriculosDoUsuario() {

        Long userId = 1L;

        List<Resume> resumes = List.of(
                mock(Resume.class),
                mock(Resume.class)
        );


        when(resumeRepositorio.FindResumeAllByUser(userId))
                .thenReturn(resumes);


        List<Resume> resultado =
                findResumeAllByUser.byId(userId);


        verify(resumeRepositorio)
                .FindResumeAllByUser(userId);


        assertSame(resumes, resultado);
    }
}
