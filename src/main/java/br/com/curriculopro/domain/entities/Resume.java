package br.com.curriculopro.domain.entities;

import br.com.curriculopro.domain.enums.State;

import java.util.ArrayList;
import java.util.List;
public class Resume {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String position;
    private String summary;
    private String city;
    private State state;
    private String cep;
    private User user;

    private List<Experience> experiences = new ArrayList<>();
    private List<Education> educations   = new ArrayList<>();
    private List<Skills> skills          = new ArrayList<>();
    private List<Link> links             = new ArrayList<>();
    private List<Language> languages     = new ArrayList<>();

    public Resume(String name, String email, String phone, String position, String city, State state, String cep) {
        this.name     = name;
        this.phone    = phone;
        this.email    = email;
        this.position = position;
        this.city     = city;
        this.state    = state;
        this.cep      = cep;
    }

    public Resume(Long id,String name, String email, String phone, String position, String summary, String city, State state, String cep, User user, List<Experience> experiences, List<Education> educations, List<Skills> skills, List<Link> links, List<Language> languages) {
        this.id          = id;
        this.name        = name;
        this.email       = email;
        this.phone       = phone;
        this.position    = position;
        this.summary     = summary;
        this.city        = city;
        this.state       = state;
        this.cep         = cep;
        this.user        = user;
        this.experiences = new ArrayList<>(experiences);
        this.educations  = new ArrayList<>(educations);
        this.skills      = new ArrayList<>(skills);
        this.links       = new ArrayList<>(links);
        this.languages   = new ArrayList<>(languages);
    }

    public void addExperience(Experience experience) {
        this.experiences.add(experience);
    }

    public void addEducation(Education education) {
        this.educations.add(education);
    }

    public void addSkills(Skills skill) {
        this.skills.add(skill);
    }

    public void addLinks(Link link) {
        this.links.add(link);
    }

    public void addLanguages(Language language) {
        this.languages.add(language);
    }

    public void deletExperience(Long experienceId) {
        experiences.removeIf(exp -> exp.getId().equals(experienceId));
    }

    public void deletEducation(Long educationId) {
        educations.removeIf(edu -> edu.getId().equals(educationId));
    }

    public void deletSkill(Long skillsId) {
        skills.removeIf(skill -> skill.getId().equals(skillsId));
    }

    public void deletLink(Long linkId) {
        links.removeIf(lin -> lin.getId().equals(linkId));
    }

    public void deletLanguages(Long languagesId) {
        languages.removeIf(la -> la.getId().equals(languagesId));
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getSummary() {
        return summary;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }

    public User getUser() {
        return user;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public List<Link> getLinks() {
        return links;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
