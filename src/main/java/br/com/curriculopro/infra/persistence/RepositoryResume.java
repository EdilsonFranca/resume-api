package br.com.curriculopro.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepositoryResume extends JpaRepository<ResumeEntity, Long> {

    @Query("""
        SELECT r
        FROM ResumeEntity r
        LEFT JOIN FETCH r.experiences ex
        WHERE r.id = :id
    """)
    Optional<ResumeEntity> findResumeByIdWithExperience(@Param("id") Long id);

    @Query("""
        SELECT r
        FROM ResumeEntity r
        LEFT JOIN FETCH r.education ed
        WHERE r.id = :id
    """)
    Optional<ResumeEntity> findResumeByIdWithEducation(Long id);

    @Query("""
        SELECT r
        FROM ResumeEntity r
        LEFT JOIN FETCH r.skills sk
        WHERE r.id = :id
    """)
    Optional<ResumeEntity> findResumeByIdWithSkill(Long id);

    @Query("""
        SELECT r
        FROM ResumeEntity r
        LEFT JOIN FETCH r.languages la
        WHERE r.id = :id
    """)
    Optional<ResumeEntity> findResumeByIdWithLanguage(Long id);

    @Query("""
    SELECT ex
    FROM ResumeEntity r
    JOIN r.experiences ex
    WHERE r.id = :id
    """)
    List<ExperienceEntity> getExperienceByIdResume(@Param("id") Long id);

    @Query("""
    SELECT ex
    FROM ResumeEntity r
    JOIN r.education ex
    WHERE r.id = :id
    """)
    List<EducationEntity> getEducationByIdResume(Long id);

    @Query("""
    SELECT ex
    FROM ResumeEntity r
    JOIN r.languages ex
    WHERE r.id = :id
    """)
    List<LanguageEntity> getLanguageByIdResume(Long id);

    @Query("""
    SELECT ex
    FROM ResumeEntity r
    JOIN r.links  ex
    WHERE r.id = :id
    """)
    List<LinkEntity> getLinkByIdResume(Long id);

    @Query("""
    SELECT ex
    FROM ResumeEntity r
    JOIN r.skills ex
    WHERE r.id = :id
    """)
    List<SkillsEntity> getSkillByIdResume(Long id);

    @Query("""
    SELECT DISTINCT r
    FROM ResumeEntity r
    LEFT JOIN FETCH r.experiences ex
    LEFT JOIN FETCH r.education ed
    LEFT JOIN FETCH r.skills sk
    LEFT JOIN FETCH r.links li
    LEFT JOIN FETCH r.languages la
    WHERE r.id = :id
    ORDER BY ex.id ,ed.id, sk.id, li.id, la.id
    """)
    Optional<ResumeEntity> FindResumeByIdWithDetails(Long id);

    @Query("""
    SELECT r.summary
    FROM ResumeEntity r
    WHERE r.id = :id
    """)
    String getSummaryByIdResume(Long id);

    @Query("""
    SELECT r
    FROM ResumeEntity r
    WHERE r.user.id = :id
    """)
    List<ResumeEntity> FindResumeAllByUser(Long id);
}



