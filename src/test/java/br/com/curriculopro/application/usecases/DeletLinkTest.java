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
class DeletLinkTest {

    @Mock
    private ResumeRepository repositorio;

    @InjectMocks
    private DeletLink deletLink;


    @Test
    void deveDeletarLinkDoCurriculo() {

        Long resumeId = 1L;
        Long linkId = 10L;

        Resume resume = mock(Resume.class);


        when(repositorio.findResumeByIdWithSkill(resumeId))
                .thenReturn(resume);


        deletLink.by(resumeId, linkId);


        verify(repositorio)
                .findResumeByIdWithSkill(resumeId);

        verify(resume)
                .deletLink(linkId);

        verify(repositorio)
                .create(resume);
    }
}