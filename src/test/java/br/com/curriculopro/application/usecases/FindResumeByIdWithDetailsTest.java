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
class FindResumeByIdWithDetailsTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private FindResumeByIdWithDetails findResumeByIdWithDetails;


    @Test
    void deveBuscarCurriculoComDetalhesPorId() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);


        when(resumeRepositorio.FindResumeByIdWithDetails(resumeId))
                .thenReturn(resume);


        Resume resultado =
                findResumeByIdWithDetails.byId(resumeId);


        verify(resumeRepositorio)
                .FindResumeByIdWithDetails(resumeId);


        assertSame(resume, resultado);
    }
}