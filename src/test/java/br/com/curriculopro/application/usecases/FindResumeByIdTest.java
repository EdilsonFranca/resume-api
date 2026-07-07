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
class FindResumeByIdTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private FindResumeById findResumeById;


    @Test
    void deveBuscarCurriculoPorId() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);


        when(resumeRepositorio.findResumeByIdWithEducation(resumeId))
                .thenReturn(resume);


        Resume resultado = findResumeById.byId(resumeId);


        verify(resumeRepositorio)
                .findResumeByIdWithEducation(resumeId);


        assertSame(resume, resultado);
    }
}