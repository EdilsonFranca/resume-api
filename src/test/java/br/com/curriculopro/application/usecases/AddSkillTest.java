package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;
import br.com.curriculopro.domain.entities.Skills;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddSkillTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private AddSkill addSkill;

    @Test
    void deveAdicionarSkillAoCurriculo() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);
        Skills skill = mock(Skills.class);

        when(resumeRepositorio.findResumeByIdWithSkill(resumeId))
                .thenReturn(resume);

        Skills resultado = addSkill.add(resumeId, skill);

        verify(resumeRepositorio).findResumeByIdWithSkill(resumeId);
        verify(resume).addSkills(skill);
        verify(resumeRepositorio).create(resume);

        assertSame(skill, resultado);
    }
}