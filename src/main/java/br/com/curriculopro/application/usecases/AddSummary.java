package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class AddSummary {
    private final ResumeRepository resumeRepositorio;

    public AddSummary(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Resume add(Long id, String summary) {
        Resume resume = resumeRepositorio.findResumeById(id);
        resume.setSummary(summary);
        resumeRepositorio.create(resume);
        return resume;
    }
}
