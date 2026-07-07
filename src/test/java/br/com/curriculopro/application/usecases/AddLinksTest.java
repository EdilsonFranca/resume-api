package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Link;
import br.com.curriculopro.domain.entities.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddLinksTest {

    @Mock
    private ResumeRepository resumeRepositorio;

    @InjectMocks
    private AddLinks addLinks;

    @Test
    void deveAdicionarLinkAoCurriculo() {

        Long resumeId = 1L;

        Resume resume = mock(Resume.class);
        Link link = mock(Link.class);

        when(resumeRepositorio.findResumeByIdWithEducation(resumeId))
                .thenReturn(resume);

        Link resultado = addLinks.add(resumeId, link);

        verify(resumeRepositorio).findResumeByIdWithEducation(resumeId);
        verify(resume).addLinks(link);
        verify(resumeRepositorio).create(resume);

        assertSame(link, resultado);
    }
}