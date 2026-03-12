package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class DeletExperience {
    private final ResumeRepository repositorio;

    public DeletExperience(ResumeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void by(Long id, Long experienceId) {
        Resume resume = repositorio.findResumeByIdWithExperience(id);
        resume.deletExperience(experienceId);
        repositorio.create(resume);
    }
}
