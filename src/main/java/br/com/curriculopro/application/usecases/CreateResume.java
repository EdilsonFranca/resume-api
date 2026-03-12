package br.com.curriculopro.application.usecases;

import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.domain.entities.Resume;
import br.com.curriculopro.domain.entities.User;

public class CreateResume {
    private final ResumeRepository repositorio;
    private final UserRepository userRepository;

    public CreateResume(ResumeRepository repositorio,UserRepository userRepository) {
        this.repositorio    = repositorio;
        this.userRepository = userRepository;
    }

    public Resume create(String email, Resume resume) {
        User user = userRepository.findUserByEmail(email);
        resume.setUser(user);
        return repositorio.create(resume);
    }
}
