package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletExperienceTest {

    @Mock
    private ResumeRepository repositorio;

    @InjectMocks
    private DeletExperience deletExperience;


    @Test
    void deveDeletarExperienciaDoCurriculo() {

        Long resumeId = 1L;
        Long experienceId = 10L;

        Resume resume = mock(Resume.class);


        when(repositorio.findResumeByIdWithExperience(resumeId))
                .thenReturn(resume);


        deletExperience.by(resumeId, experienceId);


        verify(repositorio)
                .findResumeByIdWithExperience(resumeId);

        verify(resume)
                .deletExperience(experienceId);

        verify(repositorio)
                .create(resume);
    }
}