package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.domain.entities.Skills;

import java.util.List;

public class GetSkill {
    private final ResumeRepository resumeRepositorio;

    public GetSkill(ResumeRepository resumeRepositorio) {
        this.resumeRepositorio = resumeRepositorio;
    }

    public List<Skills> byIdResume(Long id) {
        return resumeRepositorio.getSkillsByIdResume(id);
    }
}
