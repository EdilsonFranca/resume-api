package br.com.curriculopro.infra.gateways;
import br.com.curriculopro.domain.entities.*;
import br.com.curriculopro.infra.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ResumeEntityMapper {

    private final UsuarioEntityMapper usuarioEntityMapper;

    public ResumeEntityMapper(UsuarioEntityMapper usuarioEntityMapper) {
        this.usuarioEntityMapper = usuarioEntityMapper;
    }

    public ResumeEntity toEntity(Resume resume) {

            ResumeEntity entity = ResumeEntity.builder()
                    .id(resume.getId())
                    .name(resume.getName())
                    .email(resume.getEmail())
                    .city(resume.getCity())
                    .cep(resume.getCep())
                    .state(resume.getState())
                    .phone(resume.getPhone())
                    .position(resume.getPosition())
                    .summary(resume.getSummary())
                    .build();

            if (resume.getUser() != null) {
                entity.setUser(mapUser(resume.getUser()));
            }

            entity.setExperiences(new HashSet<>(mapExperiences(resume, entity)));
            entity.setEducation(new HashSet<>(mapEducation(resume, entity)));
            entity.setSkills(new HashSet<>(mapSkills(resume, entity)));
            entity.setLinks(new HashSet<>(mapLink(resume, entity)));
            entity.setLanguages(new HashSet<>(mapLanguage(resume, entity)));

            return entity;
        }

        private UserEntity mapUser(User user) {

            if (user == null) {
                return null;
            }

            UserEntity entity = new UserEntity();

            entity.setId(user.getId());
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entity.setPassword(user.getPassword());
            entity.setRole(user.getRole());
            entity.setProvider(user.getProvider());
            return entity;
        }

        private List<ExperienceEntity> mapExperiences(Resume resume, ResumeEntity entity) {
            if (resume.getExperiences() == null) return new ArrayList<>();

            return resume.getExperiences().stream()
                    .map(e -> ExperienceEntity.builder()
                            .id(e.getId())
                            .nameCompany(e.getNameCompany())
                            .start(e.getStart())
                            .end(e.getEnd())
                            .position(e.getPosition())
                            .description(e.getDescription())
                            .city(e.getCity())
                            .state(e.getState())
                            .resume(entity)
                            .build()
                    ).toList();
        }

        private List<EducationEntity> mapEducation(Resume resume, ResumeEntity entity) {
            if (resume.getEducations() == null) return new ArrayList<>();

            return resume.getEducations().stream()
                    .map(e -> EducationEntity.builder()
                            .id(e.getId())
                            .institution(e.getInstitution())
                            .course(e.getCourse())
                            .start(e.getStart())
                            .end(e.getEnd())
                            .resume(entity)
                            .build()
                    ).toList();
        }

        private List<SkillsEntity> mapSkills(Resume resume, ResumeEntity entity) {
            if (resume.getSkills() == null) return new ArrayList<>();

            return resume.getSkills().stream()
                    .map(e -> SkillsEntity.builder()
                            .id(e.getId())
                            .name(e.getName())
                            .description(e.getDescription())
                            .resume(entity)
                            .build()
                    ).toList();
        }

        private List<LinkEntity> mapLink(Resume resume, ResumeEntity entity) {
            if (resume.getLinks() == null) return new ArrayList<>();

            return resume.getLinks().stream()
                    .map(e -> LinkEntity.builder()
                            .id(e.getId())
                            .description(e.getDescription())
                            .resume(entity)
                            .build()
                    ).toList();
        }

        private List<LanguageEntity> mapLanguage(Resume resume, ResumeEntity entity) {
        if (resume.getLanguages() == null) return new ArrayList<>();

        return resume.getLanguages().stream()
                .map(e -> LanguageEntity.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .level(e.getLevel())
                        .resume(entity)
                        .build()
                ).toList();
    }

        public Resume toDomain(ResumeEntity entity) {
            return new Resume(
                    entity.getId(),
                    entity.getName(),
                    entity.getEmail(),
                    entity.getPhone(),
                    entity.getPosition(),
                    entity.getSummary(),
                    entity.getCity(),
                    entity.getState(),
                    entity.getCep(),
                    usuarioEntityMapper.toDomain(entity.getUser()),
                    mapExperiencesToDomain(entity.getExperiences()),
                    mapEducationToDomain(entity.getEducation()),
                    mapSkillsToDomain(entity.getSkills()),
                    mapLinkToDomain(entity.getLinks()),
                    mapLanguageToDomain(entity.getLanguages())
            );
     }

        public List<Experience> mapExperiencesToDomain(Collection<ExperienceEntity> entity) {
            if (entity == null) return new ArrayList<>();

            return entity.stream()
                    .map(e -> new Experience(
                            e.getId(),
                            e.getNameCompany(),
                            e.getStart(),
                            e.getEnd(),
                            e.getPosition(),
                            e.getDescription(),
                            e.getCity(),
                            e.getState()
                    )).toList();
        }

        public List<Education> mapEducationToDomain(Collection<EducationEntity> entity) {
            if (entity == null) return new ArrayList<>();

            return entity.stream()
                    .map(e -> new Education(
                            e.getId(),
                            e.getInstitution(),
                            e.getCourse(),
                            e.getStart(),
                            e.getEnd()
                    )).toList();
        }

        public List<Skills> mapSkillsToDomain(Collection<SkillsEntity> entity) {
            if (entity == null) return new ArrayList<>();

            return entity.stream()
                    .map(e -> new Skills(
                            e.getId(),
                            e.getName(),
                            e.getDescription()
                    )).toList();
        }

        public List<Link> mapLinkToDomain(Collection<LinkEntity> entity) {
            if (entity == null) return new ArrayList<>();

            return entity.stream()
                    .map(e -> new Link(
                            e.getId(),
                            e.getDescription()
                    )).toList();
        }

        public List<Language> mapLanguageToDomain(Collection<LanguageEntity> entity) {
        if (entity == null) return new ArrayList<>();

        return entity.stream()
                .map(e -> new Language(
                        e.getId(),
                        e.getName(),
                        e.getLevel()
                )).toList();
    }

}

