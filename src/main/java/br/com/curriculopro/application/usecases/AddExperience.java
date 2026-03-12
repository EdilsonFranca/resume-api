package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Experience;
import br.com.curriculopro.domain.entities.Resume;

public class AddExperience {
    private final ResumeRepository resumeRepositorio;

    public AddExperience(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Experience add(Long id, Experience experience) {
        Resume resume = resumeRepositorio.findResumeByIdWithExperience(id);
        resume.addExperience(experience);
        resumeRepositorio.create(resume);
        return experience;
    }
}
