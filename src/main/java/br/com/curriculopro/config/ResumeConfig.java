package br.com.curriculopro.config;

import br.com.curriculopro.application.gateways.PaymentRepository;
import br.com.curriculopro.application.gateways.ResumeRepository;
import br.com.curriculopro.application.gateways.UserRepository;
import br.com.curriculopro.application.usecases.*;
import br.com.curriculopro.domain.payment.PaymentGateway;
import br.com.curriculopro.infra.gateways.*;
import br.com.curriculopro.infra.persistence.RepositoryPayment;
import br.com.curriculopro.infra.persistence.RepositoryResume;
import br.com.curriculopro.infra.persistence.RepositoryUsuario;
import br.com.curriculopro.infra.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ResumeConfig {

    @Bean
    CreateResume createResume(ResumeRepository repository, UserRepository userRepository){
        return new CreateResume(repository, userRepository);
    }

    @Bean
    CreatePayment createPayment(PaymentGateway gateway, PaymentRepository repository){
        return new CreatePayment(gateway, repository);
    }

    @Bean
    PaymentRepositoryJpa createPaymentRepositoryJpa(RepositoryPayment repository, PaymentEntityMapper mapper){
        return new PaymentRepositoryJpa(repository, mapper);
    }

    @Bean
    ResumeRepositoryJpa createResumeRepositoryJpa(RepositoryResume repository, ResumeEntityMapper mapper){
        return new ResumeRepositoryJpa(repository, mapper);
    }

    @Bean
    UserRepositoryJpa createUsuarioRepositoryJpa(RepositoryUsuario repository, UsuarioEntityMapper mapper){
        return new UserRepositoryJpa(repository, mapper);
    }

    @Bean
    CreateUser createUser(UserRepository repository){
        return new CreateUser(repository);
    }

    @Bean
    AddExperience addExperience(ResumeRepository ResumeRepository){
        return new AddExperience(ResumeRepository);
    }

    @Bean
    DeletExperience deletExperience(ResumeRepository repository){
        return new DeletExperience(repository);
    }

    @Bean
    GetExperience getExperience(ResumeRepository repository){
        return new GetExperience(repository);
    }

    @Bean
    AddEducation addEducation(ResumeRepository ResumeRepository){
        return new AddEducation(ResumeRepository);
    }

    @Bean
    GetEducation getEducation(ResumeRepository repository){
        return new GetEducation(repository);
    }

    @Bean
    DeletEducation deletEducation(ResumeRepository repository){
        return new DeletEducation(repository);
    }

    @Bean
    AddSkill addSkill(ResumeRepository ResumeRepository){
        return new AddSkill(ResumeRepository);
    }

    @Bean
    DeletSkill deletSkill(ResumeRepository repository){
        return new DeletSkill(repository);
    }

    @Bean
    GetSkill getSkill(ResumeRepository repository){
        return new GetSkill(repository);
    }

    @Bean
    AddSummary addSummary(ResumeRepository repository){
        return new AddSummary(repository);
    }

    @Bean
    GetSummary getSummary(ResumeRepository repository){
        return new GetSummary(repository);
    }

    @Bean
    FindResumeAllByUser findResumeAllByUser(ResumeRepository repository){
        return new FindResumeAllByUser(repository);
    }

    @Bean
    AddLinks addLinks(ResumeRepository repository){
        return new AddLinks(repository);
    }

    @Bean
    DeletLink deletLink(ResumeRepository repository){
        return new DeletLink(repository);
    }

    @Bean
    GetLink getLink(ResumeRepository repository){
        return new GetLink(repository);
    }

    @Bean
    FindResumeById findResumeById(ResumeRepository repository){
        return new FindResumeById(repository);
    }

    @Bean
    FindResumeByIdWithDetails findResumeByIdWithDetails(ResumeRepository repository){
        return new FindResumeByIdWithDetails(repository);
    }

    @Bean
    AddLanguage addLanguage(ResumeRepository repository){
        return new AddLanguage(repository);
    }

    @Bean
    DeletLanguage deletLanguage(ResumeRepository repository){
        return new DeletLanguage(repository);
    }

    @Bean
    GetLanguage getLanguage(ResumeRepository repository){
        return new GetLanguage(repository);
    }

    @Bean
    AutenticarUsuario autenticarUsuario(UserRepository repository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtProvider){
        return new AutenticarUsuario(repository, passwordEncoder, jwtProvider );
    }

    @Bean
    ResumeEntityMapper retornaMapperResume(UsuarioEntityMapper usuarioEntityMapper){
        return new ResumeEntityMapper(usuarioEntityMapper);
    }

    @Bean
    UsuarioEntityMapper usuarioEntityMapper(){
        return new UsuarioEntityMapper();
    }

    @Bean
    PaymentEntityMapper paymentEntityMapper(){
        return new PaymentEntityMapper();
    }
}
