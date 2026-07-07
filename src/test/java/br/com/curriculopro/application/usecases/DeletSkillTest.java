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
class DeletSkillTest {

    @Mock
    private ResumeRepository repositorio;

    @InjectMocks
    private DeletSkill deletSkill;


    @Test
    void deveDeletarSkillDoCurriculo() {

        Long resumeId = 1L;
        Long skillId = 10L;

        Resume resume = mock(Resume.class);


        when(repositorio.findResumeByIdWithSkill(resumeId))
                .thenReturn(resume);


        deletSkill.by(resumeId, skillId);


        verify(repositorio)
                .findResumeByIdWithSkill(resumeId);

        verify(resume)
                .deletSkill(skillId);

        verify(repositorio)
                .create(resume);
    }
}