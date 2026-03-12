package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Experience;

import java.util.List;

public class GetExperience {
    private final ResumeRepository resumeRepositorio;

    public GetExperience(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public List<Experience> byIdResume(Long id) {
        return resumeRepositorio.getExperienceByIdResume(id);
    }
}
