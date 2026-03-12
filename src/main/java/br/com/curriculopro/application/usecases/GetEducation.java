package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Education;

import java.util.List;

public class GetEducation {
    private final ResumeRepository resumeRepositorio;

    public GetEducation(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public List<Education> byIdResume(Long id) {
        return resumeRepositorio.getEducationByIdResume(id);
    }
}
