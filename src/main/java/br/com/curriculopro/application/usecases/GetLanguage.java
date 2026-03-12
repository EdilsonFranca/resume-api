package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Language;

import java.util.List;

public class GetLanguage {
    private final ResumeRepository resumeRepositorio;

    public GetLanguage(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public List<Language> byIdResume(Long id) {
        return resumeRepositorio.getLanguageByIdResume(id);
    }
}
