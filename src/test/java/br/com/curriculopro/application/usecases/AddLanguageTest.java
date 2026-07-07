package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Language;
import br.com.curriculopro.domain.entities.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddLanguageTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private AddLanguage addLanguage;

    @Test
    void deveAdicionarIdiomaAoCurriculo() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);
        Language language = mock(Language.class);

        when(resumeRepositorio.findResumeByIdWithLanguage(resumeId))
                .thenReturn(resume);

        Language resultado = addLanguage.add(resumeId, language);

        verify(resumeRepositorio).findResumeByIdWithLanguage(resumeId);
        verify(resume).addLanguages(language);
        verify(resumeRepositorio).create(resume);

        assertSame(language, resultado);
    }
}