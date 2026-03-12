package br.com.curriculopro.application.gateways;

import br.com.curriculopro.domain.entities.*;

import java.util.List;

public interface ResumeRepository {
    Resume create(Resume resume);

    Resume findResumeById(Long id);

    Resume FindResumeByIdWithDetails(Long id);

    Resume findResumeByIdWithExperience(Long id);

    Resume findResumeByIdWithEducation(Long id);

    Resume findResumeByIdWithSkill(Long id);

    Resume findResumeByIdWithLanguage(Long id);

    List<Experience> getExperienceByIdResume(Long id);

    List<Education> getEducationByIdResume(Long id);

    List<Language> getLanguageByIdResume(Long id);

    List<Link> getLinkByIdResume(Long id);

    List<Skills> getSkillsByIdResume(Long id);

    String getSummaryByIdResume(Long id);

    List<Resume> FindResumeAllByUser(Long id);
}






