package br.com.curriculopro.application.usecases;


import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Experience;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetExperienceTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private GetExperience getExperience;


    @Test
    void deveBuscarExperienciasDoCurriculo() {

        Long resumeId = 1L;

        List<Experience> experiences = List.of(
                mock(Experience.class),
                mock(Experience.class)
        );


        when(resumeRepositorio.getExperienceByIdResume(resumeId))
                .thenReturn(experiences);


        List<Experience> resultado =
                getExperience.byIdResume(resumeId);


        verify(resumeRepositorio)
                .getExperienceByIdResume(resumeId);


        assertSame(experiences, resultado);
    }
}