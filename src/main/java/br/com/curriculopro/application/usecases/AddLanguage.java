package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Language;
import br.com.curriculopro.domain.entities.Resume;

public class AddLanguage {
    private final ResumeRepository resumeRepositorio;

    public AddLanguage(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Language add(Long id, Language language) {
        Resume resume = resumeRepositorio.findResumeByIdWithLanguage(id);
        resume.addLanguages(language);
        resumeRepositorio.create(resume);
        return language;
    }
}
