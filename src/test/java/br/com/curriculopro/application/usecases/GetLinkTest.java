package br.com.curriculopro.application.usecases;


import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Link;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetLinkTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private GetLink getLink;


    @Test
    void deveBuscarLinksDoCurriculo() {

        Long resumeId = 1L;

        List<Link> links = List.of(
                mock(Link.class),
                mock(Link.class)
        );


        when(resumeRepositorio.getLinkByIdResume(resumeId))
                .thenReturn(links);


        List<Link> resultado =
                getLink.byIdResume(resumeId);


        verify(resumeRepositorio)
                .getLinkByIdResume(resumeId);


        assertSame(links, resultado);
    }
}