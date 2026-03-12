package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Resume;

public class DeletEducation {
    private final ResumeRepository repositorio;

    public DeletEducation(ResumeRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void by(Long id, Long educationId) {
        Resume resume = repositorio.findResumeByIdWithEducation(id);
        resume.deletEducation(educationId);
        repositorio.create(resume);
    }
}
