package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Education;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetEducationTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private GetEducation getEducation;


    @Test
    void deveBuscarEducacoesDoCurriculo() {

        Long resumeId = 1L;

        List<Education> educations = List.of(
                mock(Education.class),
                mock(Education.class)
        );


        when(resumeRepositorio.getEducationByIdResume(resumeId))
                .thenReturn(educations);


        List<Education> resultado =
                getEducation.byIdResume(resumeId);


        verify(resumeRepositorio)
                .getEducationByIdResume(resumeId);


        assertSame(educations, resultado);
    }
}