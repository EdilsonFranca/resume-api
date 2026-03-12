package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;

public class GetSummary {
    private final ResumeRepository resumeRepositorio;

    public GetSummary(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public String byIdSummary (Long id) {
        return resumeRepositorio.getSummaryByIdResume(id);
    }
}
