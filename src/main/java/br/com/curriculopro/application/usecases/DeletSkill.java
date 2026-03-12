package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class DeletSkill {
    private final ResumeRepository repositorio;

    public DeletSkill(ResumeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void by(Long id, Long skillId) {
        Resume resume = repositorio.findResumeByIdWithSkill(id);
        resume.deletSkill(skillId);
        repositorio.create(resume);
    }
}
