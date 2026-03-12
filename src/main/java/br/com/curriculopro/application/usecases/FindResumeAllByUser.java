package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

import java.util.List;

public class FindResumeAllByUser {

    private final ResumeRepository resumeRepositorio;

    public FindResumeAllByUser(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public List<Resume> byId(Long id) {
        return resumeRepositorio.FindResumeAllByUser(id);
    }
}
