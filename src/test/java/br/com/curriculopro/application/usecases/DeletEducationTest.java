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
class DeletEducationTest {

    @Mock
    private ResumeRepository repositorio;

    @InjectMocks
    private DeletEducation deletEducation;


    @Test
    void deveDeletarEducacaoDoCurriculo() {

        Long resumeId = 1L;
        Long educationId = 10L;

        Resume resume = mock(Resume.class);


        when(repositorio.findResumeByIdWithEducation(resumeId))
                .thenReturn(resume);


        deletEducation.by(resumeId, educationId);


        verify(repositorio)
                .findResumeByIdWithEducation(resumeId);

        verify(resume)
                .deletEducation(educationId);

        verify(repositorio)
                .create(resume);
    }
}