package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Link;

import java.util.List;

public class GetLink {
    private final ResumeRepository resumeRepositorio;

    public GetLink(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public List<Link> byIdResume(Long id) {
        return resumeRepositorio.getLinkByIdResume(id);
    }
}
