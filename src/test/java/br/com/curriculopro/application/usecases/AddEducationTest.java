package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Education;
import br.com.curriculopro.domain.entities.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddEducationTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private AddEducation addEducation;

    @Test
    void deveAdicionarEducacaoAoCurriculo() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);
        Education education = mock(Education.class);

        when(resumeRepositorio.findResumeByIdWithEducation(resumeId))
                .thenReturn(resume);

        Education resultado = addEducation.add(resumeId, education);

        verify(resumeRepositorio).findResumeByIdWithEducation(resumeId);
        verify(resume).addEducation(education);
        verify(resumeRepositorio).create(resume);

        assertSame(education, resultado);
    }
}