package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;
import br.com.curriculopro.domain.entities.Skills;

public class AddSkill {
    private final ResumeRepository resumeRepositorio;

    public AddSkill(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Skills add(Long id, Skills skills) {
        Resume resume = resumeRepositorio.findResumeByIdWithSkill(id);
        resume.addSkills(skills);
        resumeRepositorio.create(resume);
        return skills;
    }
}
