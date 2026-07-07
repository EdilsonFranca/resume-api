package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddSummaryTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private AddSummary addSummary;

    @Test
    void deveAdicionarResumoAoCurriculo() {

        Long resumeId = 1L;
        String summary = "Desenvolvedor Java Full Stack";

        Resume resume = mock(Resume.class);

        when(resumeRepositorio.findResumeById(resumeId))
                .thenReturn(resume);

        Resume resultado = addSummary.add(resumeId, summary);

        verify(resumeRepositorio).findResumeById(resumeId);
        verify(resume).setSummary(summary);
        verify(resumeRepositorio).create(resume);

        assertSame(resume, resultado);
    }
}