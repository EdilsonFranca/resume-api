package br.com.curriculopro.application.usecases;


import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetLanguageTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private GetLanguage getLanguage;


    @Test
    void deveBuscarIdiomasDoCurriculo() {

        Long resumeId = 1L;

        List<Language> languages = List.of(
                mock(Language.class),
                mock(Language.class)
        );


        when(resumeRepositorio.getLanguageByIdResume(resumeId))
                .thenReturn(languages);


        List<Language> resultado =
                getLanguage.byIdResume(resumeId);


        verify(resumeRepositorio)
                .getLanguageByIdResume(resumeId);


        assertSame(languages, resultado);
    }
}