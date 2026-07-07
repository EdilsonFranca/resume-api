package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Skills;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetSkillTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private GetSkill getSkill;


    @Test
    void deveBuscarSkillsDoCurriculo() {

        Long resumeId = 1L;

        List<Skills> skills = List.of(
                mock(Skills.class),
                mock(Skills.class)
        );


        when(resumeRepositorio.getSkillsByIdResume(resumeId))
                .thenReturn(skills);


        List<Skills> resultado =
                getSkill.byIdResume(resumeId);


        verify(resumeRepositorio)
                .getSkillsByIdResume(resumeId);


        assertSame(skills, resultado);
    }
}