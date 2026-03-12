package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class DeletLink {
    private final ResumeRepository repositorio;

    public DeletLink(ResumeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void by(Long id, Long linkId) {
        Resume resume = repositorio.findResumeByIdWithSkill(id);
        resume.deletLink(linkId);
        repositorio.create(resume);
    }
}
