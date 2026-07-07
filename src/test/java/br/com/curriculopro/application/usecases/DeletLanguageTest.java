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
class DeletLanguageTest {

    @Mock
    private ResumeRepository repositorio;

    @InjectMocks
    private DeletLanguage deletLanguage;


    @Test
    void deveDeletarIdiomaDoCurriculo() {

        Long resumeId = 1L;
        Long languageId = 10L;

        Resume resume = mock(Resume.class);


        when(repositorio.findResumeByIdWithLanguage(resumeId))
                .thenReturn(resume);


        deletLanguage.by(resumeId, languageId);


        verify(repositorio)
                .findResumeByIdWithLanguage(resumeId);

        verify(resume)
                .deletLanguages(languageId);

        verify(repositorio)
                .create(resume);
    }
}