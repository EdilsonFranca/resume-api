package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Experience;
import br.com.curriculopro.domain.entities.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddExperienceTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private AddExperience addExperience;

    @Test
    void deveAdicionarExperienciaAoCurriculo() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);
        Experience experience = mock(Experience.class);

        when(resumeRepositorio.findResumeByIdWithExperience(resumeId)).thenReturn(resume);

        Experience resultado = addExperience.add(resumeId, experience);

        verify(resumeRepositorio).findResumeByIdWithExperience(resumeId);
        verify(resume).addExperience(experience);
        verify(resumeRepositorio).create(resume);

        assertSame(experience, resultado);
    }
}