package br.com.curriculopro.infra.controller;

import br.com.curriculopro.application.factories.GeradorPDFFactory;
import br.com.curriculopro.application.strategies.GeradorPDFStrategy;
import br.com.curriculopro.application.usecases.*;
import br.com.curriculopro.domain.entities.*;
import br.com.curriculopro.domain.enums.Modelo;
import br.com.curriculopro.infra.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/resume")
public class ResumeController {
    private final CreateResume createResume;
    private final FindResumeById findResumeById;
    private final FindResumeByIdWithDetails findResumeByIdWithDetails;
    private final FindResumeAllByUser findResumeAllByUser;

    private final AddExperience addExperience;
    private final DeletExperience deletExperience;
    private final GetExperience getExperience;

    private final AddEducation addEducation;
    private final DeletEducation deletEducation;
    private final GetEducation getEducation;

    private final AddSkill addSkill;
    private final DeletSkill deletSkill;
    private final GetSkill getSkill;

    private final AddSummary addSummary;
    private final GetSummary getSummary;

    private final AddLinks addLinks;
    private final DeletLink deletLink;
    private final GetLink getLink;

    private final AddLanguage addLanguage;
    private final DeletLanguage deletLanguage;
    private final GetLanguage  getLanguage ;


    @PostMapping
    public ResumeDto createResume(@RequestBody @NonNull ResumeDto dto, Authentication authentication) {
        Resume resume = new Resume(dto.name(), dto.email(),dto.phone(),dto.position(), dto.city(), dto.state(), dto.cep());
        String email  = authentication.getName();
        Resume salvo  = createResume.create(email, resume);
        return new ResumeDto(salvo.getId(), salvo.getName(), salvo.getPhone(), salvo.getEmail(),salvo.getPosition(), salvo.getCity(), salvo.getState(), salvo.getCep());
    }


    @GetMapping
    public ResponseEntity FindResumeAllByUser(Authentication authentication) {
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        assert user != null;
        List<Resume> res = findResumeAllByUser.byId(user.getId());

        List<ResumeDto> dto =  res.stream().map(re -> new ResumeDto(
                re.getId(), re.getName(), re.getPhone(), re.getEmail(), re.getPosition(), re.getCity(), re.getState(), re.getCep()
        )).toList();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity FindResumeById(@PathVariable Long id) {
        Resume re     = findResumeById.byId(id);
        ResumeDto dto = new ResumeDto(
                re.getId(), re.getName(), re.getPhone(), re.getEmail(), re.getPosition(), re.getCity(), re.getState(), re.getCep()
        );
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity findResumeByIdWithDetails(@PathVariable Long id) {
        Resume re = findResumeByIdWithDetails.byId(id);
        ResumeDetailsDto dto = new ResumeDetailsDto(
                re.getId(), re.getName(), re.getPhone(), re.getEmail(), re.getPosition(), re.getCity(), re.getState(), re.getCep(), re.getSummary(), re.getExperiences(), re.getEducations(), re.getSkills(), re.getLinks(), re.getLanguages()
        );
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/{id}/experiences")
    public ResponseEntity<ExperienceDto> addExperience(@PathVariable Long id, @RequestBody ExperienceDto dto) {

        Experience experience = new Experience(
                dto.id(), dto.nameCompany(), dto.start(), dto.end(), dto.position(), dto.description(), dto.city(), dto.state()
        );

        Experience sa = addExperience.add(id, experience);

        ExperienceDto response = new ExperienceDto(
                sa.getId(), sa.getNameCompany(), sa.getStart(), sa.getEnd(), sa.getPosition(), sa.getDescription(), sa.getCity(), sa.getState()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/experiences")
    public ResponseEntity getExperience(@PathVariable Long id) {
        List<Experience> experiences = getExperience.byIdResume(id);

        List<ExperienceDto> response = experiences.stream()
                .map(ex -> new ExperienceDto(
                        ex.getId(), ex.getNameCompany(), ex.getStart(), ex.getEnd(), ex.getPosition(), ex.getDescription(), ex.getCity(), ex.getState()
                )).toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/experiences/{experienceId}")
    public ResponseEntity deletExperience(@PathVariable Long id, @PathVariable Long experienceId) {
        deletExperience.by(id, experienceId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/education")
    public ResponseEntity<EducationDto> addEducation(@PathVariable Long id, @RequestBody EducationDto dto) {

        Education education = new Education(dto.id(), dto.institution(),dto.course(), dto.start(), dto.end());
        Education saved     = addEducation.add(id, education);
        EducationDto response = new EducationDto(
                saved.getId(), saved.getInstitution(), saved.getCourse(), saved.getStart(), saved.getEnd()
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/education")
    public ResponseEntity getEducation(@PathVariable Long id) {
        List<Education> educations = getEducation.byIdResume(id);

        List<EducationDto> response = educations.stream()
                .map(ed -> new EducationDto(
                        ed.getId(), ed.getInstitution(), ed.getCourse(), ed.getStart(), ed.getEnd()
                )).toList();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}/education/{educationId}")
    public ResponseEntity deletEducation(@PathVariable Long id, @PathVariable Long educationId) {
        deletEducation.by(id, educationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/skill")
    public ResponseEntity<SkillDto> addSkill(@PathVariable Long id, @RequestBody SkillDto dto) {
        Skills skill = new Skills(dto.id(), dto.name(), dto.description());
        Skills saved = addSkill.add(id, skill);
        SkillDto response = new SkillDto(
                saved.getId(), saved.getName(), saved.getDescription()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/skill")
    public ResponseEntity getSkills(@PathVariable Long id) {
        List<Skills> skills = getSkill.byIdResume(id);

        List<SkillDto> response = skills.stream()
                .map(ed -> new SkillDto(
                        ed.getId(), ed.getName(), ed.getDescription()
                )).toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/skill/{skillId}")
    public ResponseEntity deletSkill(@PathVariable Long id, @PathVariable Long skillId) {
        deletSkill.by(id, skillId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/summary")
    public ResponseEntity<Void> addSummary(@PathVariable("id") Long id,   @RequestBody SummaryDto dto) {
        addSummary.add(id, dto.summary());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/summary")
    public ResponseEntity<String> getSummary(@PathVariable("id") Long id) {
        String summary = getSummary.byIdSummary(id);
        return ResponseEntity.ok(summary);
    }

    @PostMapping("/{id}/link")
    public ResponseEntity<LinkDto>addLinks(@PathVariable("id") Long id,   @RequestBody LinkDto dto) {
        Link link        = new Link(dto.id(), dto.description());
        Link saved       = addLinks.add(id, link);
        LinkDto response = new LinkDto(saved.getId(),saved.getDescription());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/link")
    public ResponseEntity getLink(@PathVariable Long id) {
        List<Link> links = getLink.byIdResume(id);

        List<LinkDto> response = links.stream()
                .map(ed -> new LinkDto(
                        ed.getId(), ed.getDescription()
                )).toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/link/{linkId}")
    public ResponseEntity deletLink(@PathVariable Long id, @PathVariable Long linkId) {
        deletLink.by(id, linkId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/{id}/language")
    public ResponseEntity<LanguageDto> addLanguage(@PathVariable Long id, @RequestBody LanguageDto dto) {
        Language language = new Language(dto.id(), dto.name(), dto.level());
        Language saved = addLanguage.add(id, language);
        LanguageDto response = new LanguageDto(
                saved.getId(), saved.getName(), saved.getLevel()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/language")
    public ResponseEntity getLanguage(@PathVariable Long id) {
        List<Language> language = getLanguage.byIdResume(id);

        List<LanguageDto> response = language.stream()
                .map(ed -> new LanguageDto(
                        ed.getId(), ed.getName(), ed.getLevel()
                )).toList();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/language/{languageId}")
    public ResponseEntity deletLanguage(@PathVariable Long id, @PathVariable Long languageId) {
        deletLanguage.by(id, languageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pdf/{modelo}")
    public ResponseEntity<byte[]> exportPdfIntervalo(@PathVariable Long id,@PathVariable @NotNull Modelo modelo) throws Exception {
        Resume resume               = findResumeById.byId(id);
        GeradorPDFStrategy strategy = GeradorPDFFactory.criar(modelo);

        ByteArrayOutputStream pdf = strategy.generatePdf(resume);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=curriculo.pdf")
                .body(pdf.toByteArray());
    }
}