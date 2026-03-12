package br.com.curriculopro.infra.gateways;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.*;
import br.com.curriculopro.infra.persistence.*;
import br.com.curriculopro.domain.exception.ResumeNotFoundException;

import java.util.List;

public class ResumeRepositoryJpa implements ResumeRepository {

    private final RepositoryResume repositorio;
    private final ResumeEntityMapper mapper;

    public ResumeRepositoryJpa(RepositoryResume repositorio, ResumeEntityMapper mapper) {
        this.repositorio = repositorio;
        this.mapper      = mapper;
    }

    @Override
    public Resume create(Resume resume) {
        ResumeEntity entity = mapper.toEntity(resume);
        repositorio.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Resume findResumeById(Long id) {
        ResumeEntity entity = repositorio.findById(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume não encontrado para id: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public Resume FindResumeByIdWithDetails(Long id) {
        ResumeEntity entity = repositorio.FindResumeByIdWithDetails(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume não encontrado para id: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public Resume findResumeByIdWithExperience(Long id) {
        ResumeEntity entity = repositorio.findResumeByIdWithExperience(id).orElseThrow(() -> new ResumeNotFoundException("Resume não encontrado para id: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public Resume findResumeByIdWithEducation(Long id) {
        ResumeEntity entity = repositorio.findResumeByIdWithEducation(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume não encontrado para id: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public Resume findResumeByIdWithSkill(Long id) {
        ResumeEntity entity = repositorio.findResumeByIdWithSkill(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume não encontrado para id: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public Resume findResumeByIdWithLanguage(Long id) {
        ResumeEntity entity = repositorio.findResumeByIdWithLanguage(id)
                .orElseThrow(() -> new ResumeNotFoundException("Resume não encontrado para id: " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public List<Experience> getExperienceByIdResume(Long id) {
        List<ExperienceEntity> entity = repositorio.getExperienceByIdResume(id);
        return mapper.mapExperiencesToDomain(entity);
    }

    @Override
    public List<Education> getEducationByIdResume(Long id) {
        List<EducationEntity> entity = repositorio.getEducationByIdResume(id);
        return mapper.mapEducationToDomain(entity);
    }

    @Override
    public List<Language> getLanguageByIdResume(Long id) {
        List<LanguageEntity> entity = repositorio.getLanguageByIdResume(id);
        return mapper.mapLanguageToDomain(entity);
    }

    @Override
    public List<Link> getLinkByIdResume(Long id) {
        List<LinkEntity> entity = repositorio.getLinkByIdResume(id);
        return mapper.mapLinkToDomain(entity);
    }

    @Override
    public List<Skills> getSkillsByIdResume(Long id) {
        List<SkillsEntity> entity = repositorio.getSkillByIdResume(id);
        return mapper.mapSkillsToDomain(entity);
    }

    @Override
    public String getSummaryByIdResume(Long id) {
       return repositorio.getSummaryByIdResume(id);
    }

    @Override
    public List<Resume> FindResumeAllByUser(Long id) {
        List<ResumeEntity> resumeEntities = repositorio.FindResumeAllByUser(id);
        return  resumeEntities.stream().map(mapper::toDomain).toList() ;
    }
}
