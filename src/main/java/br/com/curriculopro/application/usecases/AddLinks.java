package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Link;
import br.com.curriculopro.domain.entities.Resume;

public class AddLinks {
    private final ResumeRepository resumeRepositorio;

    public AddLinks(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Link add(Long id, Link link) {
        Resume resume = resumeRepositorio.findResumeByIdWithEducation(id);
        resume.addLinks(link);
        resumeRepositorio.create(resume);
        return link;
    }
}
