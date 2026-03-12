package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Education;
import br.com.curriculopro.domain.entities.Resume;

public class AddEducation {
    private final ResumeRepository resumeRepositorio;

    public AddEducation(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public Education add(Long id, Education education) {
        Resume resume = resumeRepositorio.findResumeByIdWithEducation(id);
        resume.addEducation(education);
        resumeRepositorio.create(resume);
        return education;
    }
}
