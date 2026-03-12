package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class DeletLanguage {
    private final ResumeRepository repositorio;

    public DeletLanguage(ResumeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void by(Long id, Long languageId) {
        Resume resume = repositorio.findResumeByIdWithLanguage(id);
        resume.deletLanguages(languageId);
        repositorio.create(resume);
    }
}
