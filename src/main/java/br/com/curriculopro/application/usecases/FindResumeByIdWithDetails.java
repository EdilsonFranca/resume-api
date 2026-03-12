package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class FindResumeByIdWithDetails {
    private final ResumeRepository resumeRepositorio;

    public FindResumeByIdWithDetails(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Resume byId(Long id) {
        return resumeRepositorio.FindResumeByIdWithDetails(id);
    }

}
