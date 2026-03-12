package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class FindResumeById {
    private final ResumeRepository resumeRepositorio;

    public FindResumeById(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Resume byId(Long id) {
        return resumeRepositorio.findResumeByIdWithEducation(id);
    }
}
