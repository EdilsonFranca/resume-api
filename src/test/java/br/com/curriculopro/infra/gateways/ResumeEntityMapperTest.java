package br.com.curriculopro.infra.gateways;

import br.com.curriculopro.domain.entities.*;
import br.com.curriculopro.infra.persistence.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResumeEntityMapperTest {

    @Mock
    private UsuarioEntityMapper usuarioEntityMapper;

    @InjectMocks
    private ResumeEntityMapper mapper;


    @Test
    void deveConverterResumeParaEntity() {

        Resume resume = mock(Resume.class);

        when(resume.getId()).thenReturn(1L);
        when(resume.getName()).thenReturn("Edilson");
        when(resume.getEmail()).thenReturn("teste@email.com");
        when(resume.getCity()).thenReturn("São Paulo");
        when(resume.getCep()).thenReturn("00000-000");
        when(resume.getPhone()).thenReturn("999999999");
        when(resume.getPosition()).thenReturn("Java Developer");
        when(resume.getSummary()).thenReturn("Resumo");

        when(resume.getExperiences()).thenReturn(List.of());
        when(resume.getEducations()).thenReturn(List.of());
        when(resume.getSkills()).thenReturn(List.of());
        when(resume.getLinks()).thenReturn(List.of());
        when(resume.getLanguages()).thenReturn(List.of());

        ResumeEntity result = mapper.toEntity(resume);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Edilson", result.getName());
        assertEquals("teste@email.com", result.getEmail());

        assertNotNull(result.getExperiences());
        assertNotNull(result.getEducation());
        assertNotNull(result.getSkills());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLanguages());
    }


    @Test
    void deveConverterResumeComUsuarioParaEntity() {

        Resume resume = mock(Resume.class);
        User user = mock(User.class);

        when(resume.getUser())
                .thenReturn(user);

        when(user.getId())
                .thenReturn(1L);

        when(user.getName())
                .thenReturn("Teste");


        ResumeEntity result =
                mapper.toEntity(resume);


        assertNotNull(result.getUser());

        assertEquals(
                1L,
                result.getUser().getId()
        );

        assertEquals(
                "Teste",
                result.getUser().getName()
        );
    }


    @Test
    void deveRetornarListaVaziaQuandoExperienciasForemNulas() {

        assertTrue(
                mapper.mapExperiencesToDomain(null)
                        .isEmpty()
        );
    }


    @Test
    void deveRetornarListaVaziaQuandoEducacaoForNula() {

        assertTrue(
                mapper.mapEducationToDomain(null)
                        .isEmpty()
        );
    }


    @Test
    void deveRetornarListaVaziaQuandoSkillsForNulo() {

        assertTrue(
                mapper.mapSkillsToDomain(null)
                        .isEmpty()
        );
    }


    @Test
    void deveRetornarListaVaziaQuandoLinksForemNulos() {

        assertTrue(
                mapper.mapLinkToDomain(null)
                        .isEmpty()
        );
    }


    @Test
    void deveRetornarListaVaziaQuandoIdiomasForemNulos() {

        assertTrue(
                mapper.mapLanguageToDomain(null)
                        .isEmpty()
        );
    }


    @Test
    void deveConverterEntityParaDomain() {
        ResumeEntity entity = mock(ResumeEntity.class);

        when(entity.getExperiences())
                .thenReturn(new HashSet<>());

        when(entity.getEducation())
                .thenReturn(new HashSet<>());

        when(entity.getSkills())
                .thenReturn(new HashSet<>());

        when(entity.getLinks())
                .thenReturn(new HashSet<>());

        when(entity.getLanguages())
                .thenReturn(new HashSet<>());
        when(usuarioEntityMapper.toDomain(any())).thenReturn(null);
        Resume result = mapper.toDomain(entity);
        assertNotNull(result);
    }
}